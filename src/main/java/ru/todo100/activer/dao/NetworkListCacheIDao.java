package ru.todo100.activer.dao;

import ru.todo100.activer.model.NetworkListCacheItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NetworkListCacheIDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return NetworkListCacheItem.class;
    }
}
