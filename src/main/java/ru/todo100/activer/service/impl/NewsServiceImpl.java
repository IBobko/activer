package ru.todo100.activer.service.impl;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.NewsDao;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.service.NewsService;

import java.util.GregorianCalendar;
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
    @Override
    public void addNews(final Integer objectId, final String type, final String news) {
        NewsItem newsItem = new NewsItem();
        newsItem.setText(news);
        newsItem.setType(type);
        newsItem.setDate(new GregorianCalendar());
        newsItem.setAccountId(objectId);
        getNewsDao().save(newsItem);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<NewsItem> getNews(final List<Integer> accounts) {
        return getNewsDao().getCriteria().add(Restrictions.in("accountId",accounts)).addOrder(Order.desc("id")).setMaxResults(10).list();
    }
}
