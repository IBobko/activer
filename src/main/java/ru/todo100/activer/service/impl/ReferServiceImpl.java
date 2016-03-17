package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.AbstractDao;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Item;
import ru.todo100.activer.service.ReferService;

/**
 * Created by igor on 17.03.16.
 */
public class ReferServiceImpl implements ReferService {


    @Autowired
    AccountDao accountDao;

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public AccountItem getUserByRefer(String referCode) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccountItem.class);
        return (AccountItem)criteria.add(Restrictions.eq("referCode",referCode)).uniqueResult();
    }

}
