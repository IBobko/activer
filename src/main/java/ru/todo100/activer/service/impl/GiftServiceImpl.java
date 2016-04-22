package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.GiftDao;
import ru.todo100.activer.service.GiftService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class GiftServiceImpl implements GiftService {
    private GiftDao giftDao;

    public GiftDao getGiftDao() {
        return giftDao;
    }

    @Autowired
    public void setGiftDao(GiftDao giftDao) {
        this.giftDao = giftDao;
    }
}
