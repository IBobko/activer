package ru.todo100.activer.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.service.PhotoService;

import javax.transaction.Transactional;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PhotoServiceImpl implements PhotoService {

    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void setPhoto(final Integer accountId, final PhotoAvatarSizeData photoSize) {
        final AccountPhotoItem photoItem = new AccountPhotoItem();
        photoItem.setAccount(accountId);
        photoItem.setName(photoSize.getPhotoOriginal());
        photoItem.setNameAvatar(photoSize.getPhotoAvatar());
        photoItem.setNameMini(photoSize.getPhotoMini());
        photoItem.setNameShowing(photoSize.getPhotoShowing());
        photoItem.setNameThumbnail(photoSize.getPhotoThumbnail());
        photoItem.setAddedDate(new GregorianCalendar());
        sessionFactory.getCurrentSession().save(photoItem);
    }

    @Override
    @Transactional
    public String getPhoto(Integer accountId) {
        @SuppressWarnings("unchecked") List<Object> result = sessionFactory.
                getCurrentSession().createSQLQuery("SELECT photo_name FROM account_photo WHERE account_id = " + accountId + " ORDER BY photo_id DESC").list();
        if (result!=null && result.size() > 0) {
            return (String)result.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public PhotoAvatarSizeData getSizedPhoto(Integer accountId) {
        final AccountPhotoItem accountPhotoItem = getPhotoDao().getByAccount(accountId);
        if (accountPhotoItem != null ){
            /*Было бы неплохо, если бы за это отвечали популяторы*/
            final PhotoAvatarSizeData photoAvatarSizeData = new PhotoAvatarSizeData();
            photoAvatarSizeData.setPhotoAvatar(accountPhotoItem.getNameAvatar());
            photoAvatarSizeData.setPhotoMini(accountPhotoItem.getNameMini());
            photoAvatarSizeData.setPhotoThumbnail(accountPhotoItem.getNameThumbnail());
            photoAvatarSizeData.setPhotoShowing(accountPhotoItem.getNameShowing());
            photoAvatarSizeData.setPhotoOriginal(accountPhotoItem.getName());
            return photoAvatarSizeData;
        }
        return null;
    }


}
