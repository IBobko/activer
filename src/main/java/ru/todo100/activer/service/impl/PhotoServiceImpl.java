package ru.todo100.activer.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.service.PhotoService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setPhoto(Integer accountId, String photoName) {
        final AccountPhotoItem photoItem = new AccountPhotoItem();
        photoItem.setAccount(accountId);
        photoItem.setName(photoName);
        sessionFactory.getCurrentSession().save(photoItem);
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
