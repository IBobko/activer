package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.service.PhotoService;

import javax.transaction.Transactional;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PhotoServiceImpl implements PhotoService {

    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    @Autowired
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    private PhotoDao photoDao;

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
        getPhotoDao().save(photoItem);
    }

    @Override
    public PhotoAvatarSizeData getSizedPhoto(Integer accountId) {
        final AccountPhotoItem accountPhotoItem = getPhotoDao().getByAccount(accountId);
        final PhotoAvatarSizeData photoAvatarSizeData = new PhotoAvatarSizeData();
        if (accountPhotoItem != null ){
            /*Было бы неплохо, если бы за это отвечали популяторы*/
            photoAvatarSizeData.setPhotoAvatar(accountPhotoItem.getNameAvatar());
            photoAvatarSizeData.setPhotoMini(accountPhotoItem.getNameMini());
            photoAvatarSizeData.setPhotoThumbnail(accountPhotoItem.getNameThumbnail());
            photoAvatarSizeData.setPhotoShowing(accountPhotoItem.getNameShowing());
            photoAvatarSizeData.setPhotoOriginal(accountPhotoItem.getName());
        }
        return photoAvatarSizeData;
    }

}
