package ru.todo100.activer.service;

import ru.todo100.activer.model.NewsItem;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface NewsService {
    void addNews(final Integer objectId, final String type,final String news);
    List<NewsItem> getNews(final List<Integer> accounts);
}
