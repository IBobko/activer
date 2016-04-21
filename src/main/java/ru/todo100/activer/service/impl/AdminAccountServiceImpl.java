package ru.todo100.activer.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.AdminAccountData;
import ru.todo100.activer.data.AdminAccountQualifier;
import ru.todo100.activer.model.AdminAccountListCacheItem;
import ru.todo100.activer.service.AdminAccountService;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountServiceImpl implements AdminAccountService {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private AccountDao accountService;

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
        final AdminAccountListCacheItem adminAccountListCacheItem = new AdminAccountListCacheItem();

        accountService.getAll();
    }

    @Override
    public Long getAccountsCount(AdminAccountQualifier qualifier) {
        return null;
    }

    @Override
    public List<AdminAccountData> getAccounts(AdminAccountQualifier qualifier) {
        return null;
    }
}
