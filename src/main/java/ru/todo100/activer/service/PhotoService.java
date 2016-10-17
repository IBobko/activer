package ru.todo100.activer.service;

import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.model.AccountPhotoItem;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public interface PhotoService {
    void setPhoto(Integer accountId, PhotoAvatarSizeData photoName);
    PhotoAvatarSizeData getSizedPhoto(Integer accountId);
}
