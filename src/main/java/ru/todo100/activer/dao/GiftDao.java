package ru.todo100.activer.dao;

import ru.todo100.activer.model.GiftItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class GiftDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return GiftItem.class;
    }
}
