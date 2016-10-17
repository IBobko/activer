package ru.todo100.activer.dao;

import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.model.HappenedDisputeItem;

import javax.transaction.Transactional;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class HappenedDisputeDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return HappenedDisputeItem.class;
    }

    @Transactional
    public HappenedDisputeItem create(Integer initAccount,Integer appliedAccount) {
        final DisputeThemeItem theme = (DisputeThemeItem)getSession().createSQLQuery("SELECT * FROM (SELECT * FROM dispute_theme ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum < 2")
                .addEntity(DisputeThemeItem.class).uniqueResult();
        final HappenedDisputeItem happenedDisputeItem = new HappenedDisputeItem();
        happenedDisputeItem.setAccountAppliedId(appliedAccount);
        happenedDisputeItem.setAccountInitId(initAccount);

        if (System.currentTimeMillis() % 2 == 0) {
            happenedDisputeItem.setAppliedPosition(0);
            happenedDisputeItem.setInitPosition(1);
        } else {
            happenedDisputeItem.setAppliedPosition(1);
            happenedDisputeItem.setInitPosition(0);
        }
        happenedDisputeItem.setThemeId(theme.getId());
        happenedDisputeItem.setStartedDispute(new GregorianCalendar());

        getSession().save(happenedDisputeItem);
        return happenedDisputeItem;
    }


}
