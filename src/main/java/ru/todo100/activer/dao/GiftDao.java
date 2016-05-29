package ru.todo100.activer.dao;

import ru.todo100.activer.data.Qualifier;
import ru.todo100.activer.model.GiftItem;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class GiftDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return GiftItem.class;
    }

    public Long getCountByQualifier(Qualifier qualifier) {

        return (long)getCriteria().list().size();
    }

    @SuppressWarnings("unchecked")
    public List<GiftItem> getGiftsByQualifier(Qualifier qualifier) {
        return getCriteria().list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<GiftItem> getAll() {
        return getCriteria().list();
    }

}
