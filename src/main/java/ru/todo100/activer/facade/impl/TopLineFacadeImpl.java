package ru.todo100.activer.facade.impl;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.TopLineDao;
import ru.todo100.activer.data.TopLineData;
import ru.todo100.activer.facade.TopLineFacade;
import ru.todo100.activer.model.TopLineItem;
import ru.todo100.activer.populators.MessageAccountDataPopulator;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TopLineFacadeImpl implements TopLineFacade {

    private TopLineDao topLineDao;
    private MessageAccountDataPopulator messageAccountDataPopulator;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    public void setMessageAccountDataPopulator(MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    public TopLineDao getTopLineDao() {
        return topLineDao;
    }

    @Autowired
    public void setTopLineDao(TopLineDao topLineDao) {
        this.topLineDao = topLineDao;
    }

    public Set<TopLineData> getTopLines() {
        final List<TopLineItem> topList = getTopLineDao().getTopList();
        final Set<TopLineData> topListData = new HashSet<>();

        for (final TopLineItem topLineItem : topList) {
            final TopLineData topLineData = new TopLineData();
            topLineData.setAccount(getMessageAccountDataPopulator().populate(topLineItem.getAccount()));
            topLineData.setCreatedData(DateFormat.getDateTimeInstance().format(topLineItem.getCreatedDate().getTime()));
            topLineData.setMessage(topLineItem.getMessage());
            topListData.add(topLineData);
        }
        return topListData;
    }
}
