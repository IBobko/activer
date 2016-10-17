package ru.todo100.activer.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.model.HappenedFlirtItem;

import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class HappenedFlirtDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return HappenedFlirtItem.class;
    }

    @Transactional
    public HappenedFlirtItem create(Integer init, Integer applied) {
        final HappenedFlirtItem item = new HappenedFlirtItem();
        item.setStartedDate(new GregorianCalendar());
        item.setAccountInitId(init);
        item.setAccountAppliedId(applied);
        save(item);
        return item;
    }
}

