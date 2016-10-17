package ru.todo100.activer.dao;

import ru.todo100.activer.model.DisputeMessageItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DisputeMessageDao extends AbstractDao {

    @Override
    public Class getItemClass() {
        return DisputeMessageItem.class;
    }


}
