package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.NetworkListCacheIDao;
import ru.todo100.activer.data.PartnerData;
import ru.todo100.activer.data.PartnerInfo;
import ru.todo100.activer.data.PartnerQualifier;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AuthorityItem;
import ru.todo100.activer.model.NetworkListCacheItem;
import ru.todo100.activer.qualifier.Qualifier;
import ru.todo100.activer.service.PartnerService;
import ru.todo100.activer.service.ReferService;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("JpaQlInspection")
@Transactional
public class PartnerServiceImpl implements PartnerService {
    private ReferService referService;
    private NetworkListCacheIDao networkListCacheIDao;
    private AccountDao accountService;

    Long[] levelProfit = {14l,6l,2l,1l,1l,4l};

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public ReferService getReferService() {
        return referService;
    }

    @Autowired
    public void setReferService(ReferService referService) {
        this.referService = referService;
    }

    public NetworkListCacheIDao getNetworkListCacheIDao() {
        return networkListCacheIDao;
    }

    @Autowired
    public void setNetworkListCacheIDao(NetworkListCacheIDao networkListCacheIDao) {
        this.networkListCacheIDao = networkListCacheIDao;
    }

    @Override
    public Integer getPartnerLevel(Integer accountID) {
        return 7;
    }

    @Override
    public Integer getNetworkCount(Integer accountID) {
        return 7;
    }

    public BigDecimal getEarned(Integer accountID) {
        return new BigDecimal(7);
    }

    public BigDecimal getProfit(Integer accountID) {
        return new BigDecimal(7);
    }

    public Integer counterOfInvitedPeople(AccountItem inviting, Integer level) {
        final List<AccountItem> accounts = referService.getByReferCode(inviting.getReferCode());
        Integer invited = 0;
        if (level != 6 && accounts.size() != 0) {
            for (AccountItem account : accounts) {
                invited += counterOfInvitedPeople(account, level + 1);
            }
        }
        return accounts.size() + invited;
    }

    private boolean isSpecial(final AccountItem accountItem) {
        for (AuthorityItem authorityItem: accountItem.getAuthorities()) {
            if (authorityItem.getRole().equals("ROLE_PARTNER")) {
                return true;
            }
        }
        return false;
    }

    public List<PartnerInfo> recursive(final AccountItem inviting, final Integer level) {
        final List<AccountItem> accounts = getReferService().getByReferCode(inviting.getReferCode());
        final List<PartnerInfo> result = new ArrayList<>();
        for (AccountItem account : accounts) {
            final PartnerInfo partnerInfo = new PartnerInfo();
            partnerInfo.setId(account.getId());
            partnerInfo.setLevel(level);
            partnerInfo.setEarned(isSpecial(account) ? new BigDecimal(levelProfit[level]):new BigDecimal("0"));
            partnerInfo.setInvitedCount(getReferService().getCountOfUsedReferCode(account.getReferCode()));
            partnerInfo.setInviter(inviting.getLastName() + " " + inviting.getFirstName());
            partnerInfo.setName(account.getLastName() + " " + account.getFirstName());
            partnerInfo.setNetworkCount(counterOfInvitedPeople(account, 0));
            partnerInfo.setProfit(isSpecial(account) ? new BigDecimal(levelProfit[level]):new BigDecimal("0"));
            partnerInfo.setReferCode(account.getReferCode());
            partnerInfo.setAccountItem(account);
            partnerInfo.setInviterLevel(level - 1);

            result.add(partnerInfo);
        }

        final List<PartnerInfo> total = new ArrayList<>();
        total.addAll(result);

        if (level != 6 && accounts.size() != 0) {
            for (PartnerInfo partner : result) {
                final List<PartnerInfo> childrenPartners = recursive(partner.getAccountItem(), level + 1);
                if (childrenPartners.size() != 0) {
                    total.addAll(childrenPartners);
                }
            }
        }
        return total;
    }


    @Override
    @Transactional
    public void synchronize(Integer accountId) {
        final AccountItem current = getAccountService().get(accountId);
        final List<PartnerInfo> accounts = recursive(current, 1);

        final Query query = getNetworkListCacheIDao().getSession().createQuery("delete from NetworkListCacheItem where ownerAccountId = :id");
        query.setInteger("id", accountId).executeUpdate();

        for (final PartnerInfo partner : accounts) {
            final NetworkListCacheItem networkListCacheItem = new NetworkListCacheItem();
            networkListCacheItem.setOwnerAccountId(accountId);
            networkListCacheItem.setAccountId(partner.getId());
            networkListCacheItem.setAccountName(partner.getName());
            networkListCacheItem.setAccountLevel(partner.getLevel());
            networkListCacheItem.setEarned(partner.getEarned());
            networkListCacheItem.setProfit(partner.getProfit());
            networkListCacheItem.setInvitedCount(partner.getInvitedCount());
            networkListCacheItem.setInviterLevel(partner.getInviterLevel());
            networkListCacheItem.setInviterName(partner.getInviter());
            networkListCacheItem.setLastUpdated(new GregorianCalendar());
            networkListCacheItem.setNetworkCount(partner.getNetworkCount());
            getNetworkListCacheIDao().save(networkListCacheItem);
        }
    }


    private Criteria generateCriteria(PartnerQualifier qualifier) {
        Class<? extends PartnerQualifier> type = qualifier.getClass();
        Criteria criteria = networkListCacheIDao.getCriteria();
        for (final Method method : type.getMethods()) {
            if (method.getDeclaringClass() != type) continue;
            if (method.getName().startsWith("get") && method.getTypeParameters().length == 0) {
                final String field = method.getName().substring(3);
                final String fieldName = field.substring(0, 1).toLowerCase() + field.substring(1);
                try {
                    Object value = method.invoke(qualifier);
                    if (value == null) continue;
                    criteria.add(Restrictions.eq(fieldName, method.invoke(qualifier)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return criteria;
    }

    public List<PartnerData> getPartners(PartnerQualifier qualifier) {
        final Criteria criteria = generateCriteria(qualifier);

        if (qualifier.getStart() != null) {
            criteria.setFirstResult(qualifier.getStart());
        }

        if (qualifier.getCount() != null) {
            criteria.setMaxResults(qualifier.getCount());
        }

        if (qualifier.getOrderName() != null && qualifier.getOrder() != null) {
            if (qualifier.getOrder() == Qualifier.Order.asc) {
                criteria.addOrder(Order.asc(qualifier.getOrderName()));
            }
            if (qualifier.getOrder() == Qualifier.Order.desc) {
                criteria.addOrder(Order.desc(qualifier.getOrderName()));
            }
        }

        @SuppressWarnings("unchecked")
        final List<NetworkListCacheItem> result = criteria.list();
        final List<PartnerData> partnerData = new ArrayList<>();
        for (NetworkListCacheItem networkListCacheItem : result) {
            PartnerData partner = new PartnerData();
            partner.setName(networkListCacheItem.getAccountName());
            if (networkListCacheItem.getEarned() != null) {
                partner.setEarned(networkListCacheItem.getEarned().toString());
            }
            if (networkListCacheItem.getAccountLevel() != null) {
                partner.setLevel(networkListCacheItem.getAccountLevel().toString());
            }

            if (networkListCacheItem.getProfit() != null) {
                partner.setProfit(networkListCacheItem.getProfit().toString());
            }
            if (networkListCacheItem.getInvitedCount() != null) {
                partner.setInvitedCount(networkListCacheItem.getInvitedCount().toString());
            }

            if (networkListCacheItem.getInviterLevel() != null) {
                partner.setInviterLevel(networkListCacheItem.getInviterLevel().toString());
            }

            if (networkListCacheItem.getInviterName() != null) {
                partner.setInviter(networkListCacheItem.getInviterName());
            }
            if (networkListCacheItem.getNetworkCount() != null) {
                partner.setNetworkCount(networkListCacheItem.getNetworkCount().toString());
            }
            partnerData.add(partner);

        }
        return partnerData;
    }

    @Override
    public Long getPartnersCount(PartnerQualifier qualifier) {
        final Criteria criteria = generateCriteria(qualifier);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
