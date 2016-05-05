package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.NewsDao;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.service.NewsService;

import java.util.GregorianCalendar;

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

    @Override
    public void addNews(Integer objectId, String news) {
        NewsItem newsItem = new NewsItem();
        newsItem.setId(objectId);
        newsItem.setText(news);
        newsItem.setDate(new GregorianCalendar());
    }
}
