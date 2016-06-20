package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoDao;
import ru.todo100.activer.data.NewsData;
import ru.todo100.activer.data.NewsPhotoData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.service.PhotoService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsPopulator implements Populator<NewsItem, NewsData> {
    private MessageAccountDataPopulator messageAccountDataPopulator;

    @Autowired
    private AccountDao accountService;

    @Autowired
    private PhotoDao getPhotoDao;

    public MessageAccountDataPopulator getMessageAccountDataPopulator() {
        return messageAccountDataPopulator;
    }

    @Autowired
    public void setMessageAccountDataPopulator(MessageAccountDataPopulator messageAccountDataPopulator) {
        this.messageAccountDataPopulator = messageAccountDataPopulator;
    }

    @Override
    public NewsData populate(final NewsItem newsItem) {
        NewsData newsData;
        if (newsItem.getType().equals("PHOTO") || newsItem.getType().equals("AVATAR")) {
            newsData = new NewsPhotoData();
            if (newsItem.getText()!=null) {
                String[] photos = newsItem.getText().split(";");
                if (photos.length > 1) {
                    ((NewsPhotoData) newsData).setPhotoShowing(photos[0]);
                    ((NewsPhotoData) newsData).setPhotoThumbnail(photos[1]);
                }
            }
        } else {
            newsData = new NewsData();
        }

        final AccountItem account = accountService.get(newsItem.getAccountId());
        newsData.setAccountData(getMessageAccountDataPopulator().populate(account));
        newsData.setType(newsItem.getType());
        newsData.setDate(newsItem.getDate());
        newsData.setText(newsItem.getText());
        return newsData;
    }
}
