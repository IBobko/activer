package ru.todo100.activer.populators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.AttachmentData;
import ru.todo100.activer.data.NewsData;
import ru.todo100.activer.data.NewsPhotoData;
import ru.todo100.activer.data.NewsWallData;
import ru.todo100.activer.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsPopulator implements Populator<NewsItem, NewsData> {
    @Value(value = "${static.host.files}")
    String staticFiles;
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
        NewsData newsData;
        String text = "";
        if (newsItem instanceof PhotoNewsItem || newsItem instanceof AvatarNewsItem) {
            newsData = new NewsPhotoData();
            if (newsItem.getText() != null) {
                String[] photos = newsItem.getText().split(";");
                if (photos.length > 1) {
                    ((NewsPhotoData) newsData).setPhotoShowing(photos[0]);
                    ((NewsPhotoData) newsData).setPhotoThumbnail(photos[1]);
                }
            }
        } else if (newsItem instanceof WallNewsItem) {
            newsData = new NewsWallData();
            final NewsWallData newsWallData = (NewsWallData)newsData;
            final WallNewsItem wallNewsItem = (WallNewsItem)newsItem;
            final WallItem wall = wallNewsItem.getWall();
            final List<AttachmentData> attachments = new ArrayList<>();
            newsWallData.setAttachments(attachments);
            if (wall == null) return null;

            text = wall.getText();

            if (wallNewsItem.getWall().getAttachments() != null) {
                for (WallAttachmentItem attachmentItem: wallNewsItem.getWall().getAttachments()) {
                    AttachmentData attachment = new AttachmentData();
                    attachment.setUrl(attachmentItem.getPhoto());
                    attachments.add(attachment);
                }
            }

        } else {
            newsData = new NewsData();
        }

        final AccountItem account = accountService.get(newsItem.getAccountId());
        newsData.setAccountData(getMessageAccountDataPopulator().populate(account));
        newsData.setType(newsItem.getType());
        newsData.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(newsItem.getDate().getTime()));
        newsData.setText(newsItem.getText());


        if (newsData.getType().equals("AVATAR") && ((NewsPhotoData) newsData).getPhotoShowing() != null) {
            text = "обновил аватар<br/>\n" +
                    "<a href=\"${staticFiles}/${news.photoShowing}.jpg\"><img alt=\"First\" title=\"First image\"  style=\"max-width:300px;max-height:300px;\" src=\"${staticFiles}/${news.photoThumbnail}.\"/></a>\n";
            text = text.replace("${staticFiles}", staticFiles);
            text = text.replace("${news.photoShowing}", ((NewsPhotoData) newsData).getPhotoShowing());
            text = text.replace("${news.photoThumbnail}", ((NewsPhotoData) newsData).getPhotoThumbnail());


        }

        if (newsData.getType().equals("PHOTO") && ((NewsPhotoData) newsData).getPhotoShowing() != null) {
            text = "добавил фото<br/>\n" +
                    "<a href=\"${staticFiles}/${news.photoShowing}.jpg\"><img alt=\"First\" title=\"First image\"  style=\"max-width:300px;max-height:300px;\" src=\"${staticFiles}/${news.photoThumbnail}.\"/></a>\n";
            text = text.replace("${staticFiles}", staticFiles);
            text = text.replace("${news.photoShowing}", ((NewsPhotoData) newsData).getPhotoShowing());
            text = text.replace("${news.photoThumbnail}", ((NewsPhotoData) newsData).getPhotoThumbnail());
        }

        if (newsData.getType().equals("BOUGHT_PREMIUM")) {
            text = "купил премиум аккаунт";
        }

        newsData.setText(text);

        return newsData;
    }
}
