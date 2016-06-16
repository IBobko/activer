package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.NewsData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.NewsItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsPopulator implements Populator<NewsItem, NewsData> {
    private MessageAccountDataPopulator messageAccountDataPopulator;

    @Autowired
    private AccountDao accountService;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    public void setMessageAccountDataPopulator(MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    @Override
    public NewsData populate(final NewsItem newsItem) {
        final NewsData newsData = new NewsData();
        final AccountItem account = accountService.get(newsItem.getAccountId());
        newsData.setAccountData(getMessageAccountDataPopulator().populate(account));
        newsData.setType(newsItem.getType());
        newsData.setDate(newsItem.getDate());
        newsData.setText(newsItem.getText());
        return newsData;
    }
}
