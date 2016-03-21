package ru.todo100.activer.service;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public interface PhotoService {
    void setPhoto(Integer accountId, String photoName);
    String getPhoto(Integer accountId);
}
