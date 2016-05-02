package ru.todo100.activer.dao;

import ru.todo100.activer.model.AccountGiftItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AccountGiftDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return AccountGiftItem.class;
    }


}
