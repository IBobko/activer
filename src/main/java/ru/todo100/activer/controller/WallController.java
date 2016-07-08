package ru.todo100.activer.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.WallDao;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.PagedData;
import ru.todo100.activer.data.PhotoAvatarSizeData;
import ru.todo100.activer.data.ReceiveWallData;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.model.WallAttachmentItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.qualifier.WallQualifier;
import ru.todo100.activer.service.NewsService;
import ru.todo100.activer.util.ResizeImage;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/wall")
public class WallController {


    static private final Integer DOWNLOAD_OF = 10;
    private AccountDao accountService;
    private WallPopulator wallPopulator;
    private WallDao wallService;
    private NewsService newsService;

    public WallPopulator getWallPopulator() {
        return wallPopulator;
    }

    @Autowired
    public void setWallPopulator(WallPopulator wallPopulator) {
        this.wallPopulator = wallPopulator;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public WallDao getWallService() {
        return wallService;
    }

    @Autowired
    public void setWallService(WallDao wallService) {
        this.wallService = wallService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping("/publish")
    @ResponseBody
    public MessageData publish(final ReceiveWallData receiveWallData, final BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = getAccountService().get(receiveWallData.getId());
            final AccountItem currentAccount = getAccountService().getCurrentAccount();
            WallItem post = new WallItem();
            post.setAccount(account);
            post.setText(receiveWallData.getText());
            post.setAddedDate(new GregorianCalendar());
            post.setSender(currentAccount.getId());

            Set<WallAttachmentItem> attachments = new HashSet<>();

            WallAttachmentItem wallAttachmentItem = null;
            if (receiveWallData.getPhoto()!=null) {

                PhotoAvatarSizeData photos = upload(receiveWallData.getPhoto());
                wallAttachmentItem = new WallAttachmentItem();
                wallAttachmentItem.setWall(post);
                wallAttachmentItem.setPhoto(photos.getPhotoOriginal());
                attachments.add(wallAttachmentItem);

            }
            post.setAttachments(attachments);

            getWallService().save(post);

            getNewsService().addNews(currentAccount.getId(), "WALL", receiveWallData.getText());
            return getWallPopulator().populate(post);
        }
        return null;
    }

    public PhotoAvatarSizeData upload(MultipartFile photo) throws IOException {
        final HttpClient httpclient = HttpClientBuilder.create().build();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        final File file = new File(photo.getName());
        FileUtils.writeByteArrayToFile(file, photo.getBytes());


        final HttpPost httppost = new HttpPost("http://192.168.1.65:18080/static/upload");
        builder.addPart("image", new FileBody(file, ContentType.create(photo.getContentType())));
        httppost.setEntity(builder.build());
        final HttpResponse response = httpclient.execute(httppost);
        final StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        final String theString = writer.toString();


//        File middleFile = generateMiddlePath(file);
//        final MultipartEntityBuilder builder2 = MultipartEntityBuilder.create();
//        builder2.addPart("image", new FileBody(middleFile, ContentType.create(photo.getContentType())));
//        final HttpPost httppost2 = new HttpPost("http://192.168.1.65:18080/static/upload");
//        httppost2.setEntity(builder2.build());
//        final HttpResponse response2 = httpclient.execute(httppost2);
//        final StringWriter writer2 = new StringWriter();
//        IOUtils.copy(response2.getEntity().getContent(), writer2, "UTF-8");
//        final String theString2 = writer2.toString();

        PhotoAvatarSizeData photoAvatarSizeData = new PhotoAvatarSizeData();

        photoAvatarSizeData.setPhotoOriginal(theString);
        //photoAvatarSizeData.setPhotoThumbnail(theString2);

        return photoAvatarSizeData;
    }

    private File generateMiddlePath(final File original) {
        String newName = RandomStringUtils.randomAlphanumeric(6);
        final File newFile = new File(newName);
        ResizeImage.crop(original, newFile, "jpg", 300, 200);
        return newFile;
    }

    @RequestMapping("/remove/{id}")
    @ResponseBody
    public String remove(@PathVariable final Integer id) throws IOException {
        getWallService().delete(id);
        return id.toString();
    }

    @RequestMapping("/ajax{id}")
    @ResponseBody
    public PagedData<MessageData> ajax(@PathVariable final Integer id, final PagedForm pagedForm, final HttpSession session) {
        final PagedData<MessageData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());

        final WallQualifier qualifier = new WallQualifier();
        if (id != null && !id.equals(0)) {
            qualifier.setAccountId(id);
        } else {
            qualifier.setAccountId(getAccountService().getCurrentProfileData(session).getId());
        }

        qualifier.setStart(pagedForm.getPage() * DOWNLOAD_OF);
        qualifier.setCount(DOWNLOAD_OF);
        final List<MessageData> wall = new ArrayList<>();
        final List<WallItem> posts = getWallService().getByQualifier(qualifier);
        for (final WallItem item : posts) {
            final MessageData data = getWallPopulator().populate(item);
            wall.add(data);
        }
        pagedData.setElements(wall);
        return pagedData;
    }
}



