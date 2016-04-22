package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.AdminAccountListCacheDao;
import ru.todo100.activer.data.AdminAccountData;
import ru.todo100.activer.data.AdminAccountQualifier;
import ru.todo100.activer.data.PartnerInfo;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AdminAccountListCacheItem;
import ru.todo100.activer.service.AdminAccountService;
import ru.todo100.activer.service.PartnerService;
import ru.todo100.activer.service.ReferService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountServiceImpl implements AdminAccountService {
    @Autowired
    private ReferService referService;
    private PartnerService partnerService;
    @Autowired
    private AdminAccountListCacheDao adminAccountListCacheDao;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountDao accountService;

    public PartnerService getPartnerService() {
        return partnerService;
    }

    @Autowired
    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    public AdminAccountListCacheDao getAdminAccountListCacheDao() {
        return adminAccountListCacheDao;
    }

    public void setAdminAccountListCacheDao(AdminAccountListCacheDao adminAccountListCacheDao) {
        this.adminAccountListCacheDao = adminAccountListCacheDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public void synchronize() {
        final Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("delete from admin_account_list_cache").executeUpdate();
        tx.commit();

        tx = session.beginTransaction();
        List<AccountItem> accounts = accountService.getAll();
        for (AccountItem accountItem : accounts) {
            final AdminAccountListCacheItem adminAccountListCacheItem = new AdminAccountListCacheItem();
            adminAccountListCacheItem.setAccountItem(accountItem);
            adminAccountListCacheItem.setNetworkCount(getPartnerService().getNetworkCount(accountItem.getId()));
            List<PartnerInfo> partners = getPartnerService().recursive(accountItem.getReferCode(), 6);
            adminAccountListCacheItem.setNetworkCount(partners.size());
            AccountItem inviter = referService.getUserByRefer(accountItem.getUsedReferCode());
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

    private Criteria generateCriteria(AdminAccountQualifier qualifier) {
        Class<? extends AdminAccountQualifier> type = qualifier.getClass();
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(AdminAccountListCacheItem.class);
//        for (final Method method : type.getMethods()) {
//            if (method.getDeclaringClass() != type) continue;
//            if (method.getName().startsWith("get") && method.getTypeParameters().length == 0) {
//                final String field = method.getName().substring(3);
//                final String fieldName = field.substring(0, 1).toLowerCase() + field.substring(1);
//                try {
//                    Object value = method.invoke(qualifier);
//                    if (value == null) continue;
//                    criteria.add(Restrictions.eq(fieldName, method.invoke(qualifier)));
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return criteria;
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AdminAccountData> getAccounts(AdminAccountQualifier qualifier) {
        List<AdminAccountListCacheItem> items = getSessionFactory().getCurrentSession().createCriteria(AdminAccountListCacheItem.class).list();
        List<AdminAccountData> result = new ArrayList<>();
        for (AdminAccountListCacheItem item : items) {
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
}
