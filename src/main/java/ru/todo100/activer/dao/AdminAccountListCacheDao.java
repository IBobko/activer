package ru.todo100.activer.dao;

import ru.todo100.activer.model.AdminAccountListCacheItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountListCacheDao extends AbstractDao {

    @Override
    public Class getItemClass() {
        return AdminAccountListCacheItem.class;
    }

}
