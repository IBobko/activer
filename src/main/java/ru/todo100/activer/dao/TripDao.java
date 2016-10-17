package ru.todo100.activer.dao;

import ru.todo100.activer.model.TripItem;

import javax.transaction.Transactional;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class TripDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return TripItem.class;
    }
}
