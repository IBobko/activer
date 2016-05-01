package ru.todo100.activer.dao;

import ru.todo100.activer.model.GiftCategoryItem;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class GiftCategoryDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return GiftCategoryItem.class;
    }

    @SuppressWarnings("unchecked")
    public List<GiftCategoryItem> getCategories() {
        return getCriteria().list();
    }
}
