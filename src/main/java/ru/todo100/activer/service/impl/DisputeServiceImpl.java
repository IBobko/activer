package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.DisputeThemeDao;
import ru.todo100.activer.data.DisputeThemeData;
import ru.todo100.activer.form.DisputeThemeForm;
import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.qualifier.DisputeThemeQualifier;
import ru.todo100.activer.service.DisputeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class DisputeServiceImpl implements DisputeService {
    private DisputeThemeDao disputeThemeDao;

    public DisputeThemeDao getDisputeThemeDao() {
        return disputeThemeDao;
    }

    @Autowired
    public void setDisputeThemeDao(DisputeThemeDao disputeThemeDao) {
        this.disputeThemeDao = disputeThemeDao;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<DisputeThemeItem> getItemByQualifier(DisputeThemeQualifier qualifier) {

        final Criteria criteria = disputeThemeDao.getCriteria();
        if (qualifier.getStart() != null) {
            criteria.setFirstResult(qualifier.getStart());
        }

        if (qualifier.getCount() != null) {
            criteria.setMaxResults(qualifier.getCount());
        }

        return criteria.list();
    }

    @Override
    public Long getCountByQualifier(DisputeThemeQualifier qualifier) {
        final Criteria criteria = getDisputeThemeDao().getCriteria();
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<DisputeThemeData> getDataByQualifier(DisputeThemeQualifier qualifier) {
        final List<DisputeThemeItem> items = getItemByQualifier(qualifier);
        final List<DisputeThemeData> result = new ArrayList<>();
        for (DisputeThemeItem item : items) {
            final DisputeThemeData data = new DisputeThemeData();
            data.setId(item.getId().toString());
            data.setName(item.getName());
            data.setPosition1(item.getPosition1());
            data.setPosition2(item.getPosition2());
            result.add(data);
        }
        return result;
    }

    @Override
    public DisputeThemeItem editDispute(DisputeThemeForm form) {
        DisputeThemeItem disputeThemeItem;
        if (form.getId() == null) {
            disputeThemeItem = new DisputeThemeItem();
        } else {
            disputeThemeItem = getDisputeThemeDao().getSession().get(DisputeThemeItem.class, form.getId());
            if (disputeThemeItem == null) {
                disputeThemeItem = new DisputeThemeItem();
            }
        }
        disputeThemeItem.setName(form.getName());
        disputeThemeItem.setPosition1(form.getPosition1());
        disputeThemeItem.setPosition2(form.getPosition2());
        getDisputeThemeDao().getSession().save(disputeThemeItem);
        return disputeThemeItem;
    }

    @Override
    public DisputeThemeItem get(Integer id) {
        return (DisputeThemeItem) getDisputeThemeDao().getSession().get(getDisputeThemeDao().getItemClass(), id);
    }

    @Override
    public void delete(Integer id) {
        getDisputeThemeDao().delete(id);
    }
}
