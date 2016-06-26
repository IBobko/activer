package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value(value = "${static.host.files}")
    String staticFiles;

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

        String text = "";

        if (newsData.getType().equals("WALL")) {
            text = "<strong>написал:</strong><br/><span style=\"font-weight: normal;\">${news.text}</span>";
            text = text.replace("${news.text}",newsItem.getText());
        }

        if (newsData.getType().equals("AVATAR") && ((NewsPhotoData) newsData).getPhotoShowing()!=null) {
            text = "обновил аватар<br/>\n" +
                    "        <a href=\"${staticFiles}/${news.photoShowing}.jpg\"><img alt=\"First\" title=\"First image\"  style=\"width:200px\" src=\"${staticFiles}/${news.photoThumbnail}.\"/></a>\n";
            text = text.replace("${staticFiles}",staticFiles);
            text = text.replace("${news.photoShowing}", ((NewsPhotoData) newsData).getPhotoShowing());
            text = text.replace("${news.photoThumbnail}",((NewsPhotoData) newsData).getPhotoThumbnail());


        }

        if (newsData.getType().equals("PHOTO") && ((NewsPhotoData) newsData).getPhotoShowing()!=null) {
            text = "                добавил фото\n" +
                    "                <br/>\n" +
                    "        <a href=\"${staticFiles}/${news.photoShowing}.jpg\"><img alt=\"First\" title=\"First image\"  style=\"width:200px\" src=\"${staticFiles}/${news.photoThumbnail}.\"/></a>\n";
            text = text.replace("${staticFiles}",staticFiles);
            text = text.replace("${news.photoShowing}", ((NewsPhotoData) newsData).getPhotoShowing());
            text = text.replace("${news.photoThumbnail}",((NewsPhotoData) newsData).getPhotoThumbnail());
        }

        if (newsData.getType().equals("BOUGHT_PREMIUM")) {
            text =  "купил премиум аккаунт";
        }

        newsData.setText(text);

        return newsData;
    }
}
