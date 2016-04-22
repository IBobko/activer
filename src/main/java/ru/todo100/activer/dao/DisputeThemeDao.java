package ru.todo100.activer.dao;

import ru.todo100.activer.model.DisputeThemeItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DisputeThemeDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return DisputeThemeItem.class;
    }
}
