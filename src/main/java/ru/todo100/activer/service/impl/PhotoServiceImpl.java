package ru.todo100.activer.service.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.service.PhotoService;

import java.util.List;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@javax.transaction.Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @javax.transaction.Transactional
    public void setPhoto(Integer accountId, String photoName) {
        Long id = System.currentTimeMillis();

        final SQLQuery s =sessionFactory.
                getCurrentSession().
                createSQLQuery("INSERT INTO account_photo (photo_id,account_id,photo_name) VALUES('"+id+"','"+accountId+"','"+photoName+"')");
        s.executeUpdate();
    }

    @Override
    public String getPhoto(Integer accountId) {
        List<Object> result = sessionFactory.
                getCurrentSession().createSQLQuery("SELECT photo_name FROM account_photo WHERE account_id = " + accountId + " ORDER BY photo_id DESC").list();
        if (result!=null && result.size() > 0) {
            return (String)result.get(0);
        }
        return null;
    }
}
