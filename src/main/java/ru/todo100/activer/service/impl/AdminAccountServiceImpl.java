package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.AdminAccountData;
import ru.todo100.activer.data.AdminAccountQualifier;
import ru.todo100.activer.data.PartnerInfo;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AdminAccountListCacheItem;
import ru.todo100.activer.model.AuthorityItem;
import ru.todo100.activer.service.AdminAccountService;
import ru.todo100.activer.service.PartnerService;
import ru.todo100.activer.service.ReferService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountServiceImpl implements AdminAccountService {
    private ReferService referService;
    private PartnerService partnerService;
    private SessionFactory sessionFactory;
    private AccountDao accountService;

    private ReferService getReferService() {
        return referService;
    }

    @Autowired
    public void setReferService(ReferService referService) {
        this.referService = referService;
    }

    private PartnerService getPartnerService() {
        return partnerService;
    }

    @Autowired
    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public void synchronize() {
        final Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("DELETE FROM admin_account_list_cache").executeUpdate();
        tx.commit();

        tx = session.beginTransaction();
        List<AccountItem> accounts = accountService.getAll();
        for (AccountItem accountItem : accounts) {
            final AdminAccountListCacheItem adminAccountListCacheItem = new AdminAccountListCacheItem();
            adminAccountListCacheItem.setAccountItem(accountItem);
            adminAccountListCacheItem.setNetworkCount(getPartnerService().getNetworkCount(accountItem.getId()));
            List<PartnerInfo> partners = getPartnerService().recursive(accountItem, 1);
            adminAccountListCacheItem.setNetworkCount(partners.size());
            AccountItem inviter = getReferService().getUserByRefer(accountItem.getUsedReferCode());
            if (inviter != null) {
                adminAccountListCacheItem.setInviterName(inviter.getLastName() + " " + inviter.getFirstName());
                adminAccountListCacheItem.setInviterId(inviter.getId());
            }
            session.save(adminAccountListCacheItem);
        }
        tx.commit();
    }

    @Override
    @Transactional
    public Long getAccountsCount(AdminAccountQualifier qualifier) {
        final Criteria criteria = generateCriteria(qualifier);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria generateCriteria(final AdminAccountQualifier qualifier) {
        final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(AdminAccountListCacheItem.class);
        if (qualifier.getOnOffline() != null) {
            criteria.createAlias("accountItem", "accountItem");
            criteria.add(Restrictions.sqlRestriction("isOnline(accountite1_.account_last_activity) = " + (qualifier.getOnOffline() ? 1 : 0)));
        }
        return criteria;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AdminAccountData> getAccounts(AdminAccountQualifier qualifier) {
        final Criteria criteria = generateCriteria(qualifier);
        if (qualifier.getCount() != null) {
            criteria.setMaxResults(qualifier.getCount());
        }

        if (qualifier.getStart() != null) {
            criteria.setFirstResult(qualifier.getStart());
        }

        final List<AdminAccountListCacheItem> items = criteria.list();
        List<AdminAccountData> result = new ArrayList<>();
        for (final AdminAccountListCacheItem item : items) {
            AdminAccountData data = new AdminAccountData();
            data.setFirstName(item.getAccountItem().getFirstName());
            data.setLastName(item.getAccountItem().getLastName());
            data.setEmail(item.getAccountItem().getEmail());
            if (item.getNetworkCount() != null) {
                data.setNetworkCount(item.getNetworkCount().toString());
            }
            data.setInviterName(item.getInviterName());
            if (item.getInviterId() != null) {
                data.setInviterId(item.getInviterId().toString());
            }
            /*@TODO переделать на код из ресурсов */
            data.setType("Бесплатный");
            Set<AuthorityItem> authorities = item.getAccountItem().getAuthorities();

            for (AuthorityItem authorityItem : authorities) {
                if (authorityItem.getRole().equals("ROLE_PARTNER")) {
                    data.setType("Партнер");
                }
                if (authorityItem.getRole().equals("ROLE_CREATOR")) {
                    data.setType("Создатель");
                    break;
                }
            }

            Calendar lastActivity = item.getAccountItem().getLastActivity();
            if (lastActivity == null) {
                data.setOnoffline("offline");
            } else {
                Calendar now = new GregorianCalendar();
                long diff = now.getTimeInMillis() - lastActivity.getTimeInMillis();

                if (diff > 900000) {
                    data.setOnoffline("offline");
                } else {
                    data.setOnoffline("online");
                }

            }

            result.add(data);
        }
        return result;
    }

    @Override
    @Transactional
    public Long getTotalAccountAmount() {
        return (Long) getSessionFactory().getCurrentSession().createCriteria(AccountItem.class)
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional
    public Long getTotalOnlineAccountAmount() {
        final NativeQuery query = getSessionFactory().getCurrentSession().createNativeQuery("SELECT COUNT(*)\n" +
                "FROM ACCOUNT WHERE ((extract(DAY    FROM (systimestamp - TIMESTAMP '1970-01-01 00:00:00')) * 86400000\n" +
                "+ extract(HOUR   FROM (systimestamp - TIMESTAMP '1970-01-01 00:00:00')) * 3600000\n" +
                "+ extract(MINUTE FROM (systimestamp - TIMESTAMP '1970-01-01 00:00:00')) * 60000\n" +
                "+ extract(SECOND FROM (systimestamp - TIMESTAMP '1970-01-01 00:00:00')) * 1000)\n" +
                "-\n" +
                "(extract(DAY    FROM (ACCOUNT_LAST_ACTIVITY - TIMESTAMP '1970-01-01 00:00:00')) * 86400000\n" +
                "+ extract(HOUR   FROM (ACCOUNT_LAST_ACTIVITY - TIMESTAMP '1970-01-01 00:00:00')) * 3600000\n" +
                "+ extract(MINUTE FROM (ACCOUNT_LAST_ACTIVITY - TIMESTAMP '1970-01-01 00:00:00')) * 60000\n" +
                "+ extract(SECOND FROM (ACCOUNT_LAST_ACTIVITY - TIMESTAMP '1970-01-01 00:00:00')) * 1000)) < 600000");

        return ((BigDecimal) query.uniqueResult()).longValue();
    }
}
