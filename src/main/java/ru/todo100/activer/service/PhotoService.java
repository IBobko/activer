package ru.todo100.activer.service;

import ru.todo100.activer.data.PhotoAvatarSizeData;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public interface PhotoService {
    void setPhoto(Integer accountId, PhotoAvatarSizeData photoName);
    String getPhoto(Integer accountId);
    PhotoAvatarSizeData getSizedPhoto(Integer accountId);
}
