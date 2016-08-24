package ru.todo100.activer.service.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.NewsDao;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.qualifier.Qualifier;
import ru.todo100.activer.service.NewsService;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao;

    public NewsDao getNewsDao() {
        return newsDao;
    }

    @Autowired
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Transactional
    public void addNews(final NewsItem newsItem) {
        getNewsDao().save(newsItem);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<NewsItem> getNews(final List<Integer> accounts) {
        return getNewsDao().getCriteria().add(Restrictions.in("accountId",accounts)).addOrder(Order.desc("id")).setMaxResults(10).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<NewsItem> getByQualifier(Qualifier qualifier) {
        final Criteria criteria = getNewsDao().getCriteria();
        if (qualifier.getParams()!=null) {
            HashMap<String, Object> params = qualifier.getParams();
            if (params.containsKey("accounts")) {
                criteria.add(Restrictions.in("accountId",(List)params.get("accounts")));
            }
        }

        if (qualifier.getOrder() != null && qualifier.getOrderName() != null) {
            if (qualifier.getOrder().equals(Qualifier.Order.asc)) {
                criteria.addOrder(Order.asc(qualifier.getOrderName()));
            } else {
                criteria.addOrder(Order.desc(qualifier.getOrderName()));
            }
        }
        if (qualifier.getStart() != null) {
            criteria.setFirstResult(qualifier.getStart());
        }

        if (qualifier.getCount() != null) {
            criteria.setMaxResults(qualifier.getCount());
        }
        return criteria.list();
    }
}
