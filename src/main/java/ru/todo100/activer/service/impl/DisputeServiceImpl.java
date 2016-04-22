package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.DisputeThemeDao;
import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.qualifier.DisputeThemeQualifier;
import ru.todo100.activer.service.DisputeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DisputeServiceImpl implements DisputeService {
    private DisputeThemeDao disputeThemeDao;

    public DisputeThemeDao getDisputeThemeDao() {
        return disputeThemeDao;
    }

    @Autowired
    public void setDisputeThemeDao(DisputeThemeDao disputeThemeDao) {
        this.disputeThemeDao = disputeThemeDao;
    }


    @Override
    public List<DisputeThemeItem> getItemByQualifier(DisputeThemeQualifier qualifier) {
        return new ArrayList<>();
    }
}
