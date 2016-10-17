package ru.todo100.activer.dao;

import ru.todo100.activer.model.FlirtMessageItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FlirtMessageDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return FlirtMessageItem.class;
    }
}
