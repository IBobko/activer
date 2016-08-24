package ru.todo100.activer.service;

import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.qualifier.Qualifier;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface NewsService {
    void addNews(final NewsItem newsItem);
    List<NewsItem> getNews(final List<Integer> accounts);
    List<NewsItem> getByQualifier(Qualifier qualifier);
}
