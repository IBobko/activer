package ru.todo100.activer.dao;

import ru.todo100.activer.model.NewsItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return NewsItem.class;
    }
}
