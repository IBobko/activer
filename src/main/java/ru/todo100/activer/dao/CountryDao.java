package ru.todo100.activer.dao;

import ru.todo100.activer.model.CountryItem;


import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class CountryDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return CountryItem.class;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<CountryItem> getAll() {
        return getCriteria().list();
    }
}
