package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.AbstractDao;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Item;
import ru.todo100.activer.service.ReferService;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class ReferServiceImpl implements ReferService {

    @Autowired
    AccountDao accountService;

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public AccountItem getUserByRefer(String referCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccountItem.class);
        return (AccountItem)criteria.add(Restrictions.eq("referCode",referCode)).uniqueResult();
    }

    @Override
    @Transactional
    public Long getCountOfUsedReferCode(String referCode) {
        return (Long)accountService.getCriteria().add(Restrictions.eq("referCode", referCode)).setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<AccountItem> getByReferCode(String referCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccountItem.class);
        return criteria.add(Restrictions.eq("usedReferCode",referCode)).list();
    }


}
