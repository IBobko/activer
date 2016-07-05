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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoAlbumDao;
import ru.todo100.activer.dao.PhotosDao;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.form.PhotoAlbumForm;
import ru.todo100.activer.model.PhotoAlbumItem;
import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.service.NewsService;
import ru.todo100.activer.util.ResizeImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/photos")
public class PhotosPageController {

    private PhotoAlbumDao photoAlbumDao;
    @Autowired
    private PhotosDao photosDao;
    @Autowired
    private AccountDao accountService;

    public PhotoAlbumDao getPhotoAlbumDao() {
        return photoAlbumDao;
    }

    @Autowired
    public void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
        this.photoAlbumDao = photoAlbumDao;
    }

    @RequestMapping
    public String index(final Model model, @RequestParam(defaultValue = "0") Integer accountId) {
        model.addAttribute("pageType", "photos");

        if (accountId.equals(0)) {
            accountId = accountService.getCurrentAccount().getId();
        }

        final List<PhotoAlbumItem> albums = getPhotoAlbumDao().getAlbumsByAccount(accountId);
        model.addAttribute("albums", albums);
        model.addAttribute("accountId",accountId);
        return "photos/index";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam final Integer photoId) {
        /*todo сделать чекалку, на то, что этот пользователь может удалять эту фото*/
        photosDao.delete(photoId);
        return photoId.toString();
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Model model, final HttpServletRequest request) {
        model.addAttribute("pageType", "photos");

        final PhotoAlbumForm photoAlbumForm = new PhotoAlbumForm();
        Integer albumId = null;
        try {
            albumId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }

        if (albumId != null) {
            final PhotoAlbumItem photoAlbumItem = (PhotoAlbumItem) photoAlbumDao.get(albumId);
            photoAlbumForm.setDescription(photoAlbumItem.getDescription());
            photoAlbumForm.setId(photoAlbumItem.getId());
            photoAlbumForm.setName(photoAlbumItem.getName());

            if (photoAlbumItem.getCover() != null) {
                /*todo Возможно стоит использовать PhotoForm. Стоит проверить создает ли он форму или нет при отправке с формы*/
                photoAlbumForm.setPhotoName(photoAlbumItem.getCover().getMiddlePath());
                photoAlbumForm.setPhotoId(photoAlbumItem.getCover().getId());
            }
        }


        model.addAttribute("photoAlbumForm", photoAlbumForm);
        return "photos/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postAlbum(final PhotoAlbumForm photoAlbumForm, final BindingResult bindingResult, final HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            final PhotoAlbumItem photoAlbumItem;
            if (photoAlbumForm.getId() != null) {
                photoAlbumItem = (PhotoAlbumItem) photoAlbumDao.get(photoAlbumForm.getId());
            } else {
                photoAlbumItem = new PhotoAlbumItem();
            }
            photoAlbumItem.setName(photoAlbumForm.getName());
            photoAlbumItem.setDescription(photoAlbumForm.getName());
            ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
            photoAlbumItem.setAccountId(profileData.getId());
            if (photoAlbumForm.getPhotoId() != null) {
                /*
                * todo сделать проверку существует ли такая фото, принадлежит ли фото владельцу альбома
                * */
                final PhotoItem photoItem = new PhotoItem();
                photoItem.setId(photoAlbumForm.getPhotoId());
                photoAlbumItem.setCover(photoItem);
            }
            photoAlbumDao.save(photoAlbumItem);
        }
        return "redirect:/photos";
    }

    @RequestMapping(value = "/album{id}", method = RequestMethod.GET)
    public String album(@PathVariable final Integer id, Model model, final HttpSession session, @RequestParam(defaultValue = "0") Integer accountId) {
        if (accountId == 0) {
            accountId = accountService.getCurrentProfileData(session).getId();
        }
        final PhotoAlbumItem album = getPhotoAlbumDao().getAlbum(accountId, id);
        if (album == null) {
            return "redirect:/photos?accountId=" + accountId;
        }
        model.addAttribute("photos", album.getPhotos());
        model.addAttribute("album", album);
        return "photos/album";
    }

    private File generateMiddlePath(final File original) {
        String newName = RandomStringUtils.randomAlphanumeric(6);
        final File newFile = new File(newName);
        ResizeImage.crop(original, newFile, "jpg", 300, 200);
        return newFile;
    }

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile photo, @RequestParam(value = "album", required = false) Integer album) throws IOException {
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


        File middleFile = generateMiddlePath(file);
        final MultipartEntityBuilder builder2 = MultipartEntityBuilder.create();
        builder2.addPart("image", new FileBody(middleFile, ContentType.create(photo.getContentType())));
        final HttpPost httppost2 = new HttpPost("http://192.168.1.65:18080/static/upload");
        httppost2.setEntity(builder2.build());
        final HttpResponse response2 = httpclient.execute(httppost2);
        final StringWriter writer2 = new StringWriter();
        IOUtils.copy(response2.getEntity().getContent(), writer2, "UTF-8");
        final String theString2 = writer2.toString();


        PhotoItem photo1 = new PhotoItem();
        photo1.setPath(theString);
        photo1.setMiddlePath(theString2);
        photo1.setSmallPath(theString);


        photo1.setAccountId(accountService.getCurrentAccount().getId());
        photo1.setAlbumId(album);

        photosDao.save(photo1);

        JSONObject result = new JSONObject();
        result.put("originalPath", theString);
        result.put("middlePath", theString2);
        result.put("id", photo1.getId());

        newsService.addNews(accountService.getCurrentAccount().getId(),"PHOTO",theString + ";" + theString2);

        return result.toString();
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping("/{id}/photo-{photo}")
    public String photo() {
        return "photos/photo";
    }


    @ResponseBody
    @RequestMapping(value = "/ajax")
    public List<PhotoItem> ajax(final HttpServletRequest request) {
        try {
            final Integer album = Integer.parseInt(request.getParameter("album"));
            ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
            return photosDao.getByAccountAndAlbum(profileData.getId(), album);
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    @ResponseBody
    @RequestMapping(value = "/ajax2")
    public List<PhotoItem> ajax2(final HttpServletRequest request) {
        try {
            final ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
            return photosDao.getByAccount(profileData.getId());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
