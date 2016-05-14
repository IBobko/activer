package ru.todo100.activer.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
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
import ru.todo100.activer.form.PhotoForm;
import ru.todo100.activer.model.PhotoAlbumItem;
import ru.todo100.activer.model.PhotoItem;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private PhotoAlbumDao photoAlbumDao;

    @Autowired
    private PhotosDao photosDao;

    @Autowired
    private AccountDao accountService;

    @RequestMapping("")
    public String index(final Model model) {
        model.addAttribute("pageType","photos");
        Integer accountId = accountService.getCurrentAccount().getId();
        List<PhotoAlbumItem> albums = photoAlbumDao.getAlbumsByAccount(accountId);
        model.addAttribute("albums", albums);
        return "photos/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Model model, final HttpServletRequest request) {
        model.addAttribute("pageType","photos");

        final PhotoAlbumForm photoAlbumForm = new PhotoAlbumForm();
        Integer albumId = null;
        try {
            albumId = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException ignored){}

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
            final PhotoItem photoItem = new PhotoItem();
            photoItem.setId(photoAlbumForm.getPhotoId());
            photoAlbumItem.setCover(photoItem);
            photoAlbumDao.save(photoAlbumItem);
        }
        return "redirect:/photos";
    }

    @RequestMapping(value = "/album{id}", method = RequestMethod.GET)
    public String album(@PathVariable Integer id,Model model) {
        final Integer accountId = accountService.getCurrentAccount().getId();
        List<PhotoItem> photos = photosDao.getByAccountAndAlbum(accountId, id);
        model.addAttribute("photos",photos);
        model.addAttribute("album",id);
        return "photos/album";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file", required = false) MultipartFile photo,@RequestParam(value = "album", required = false)Integer album) throws IOException {
        final HttpClient httpclient = HttpClientBuilder.create().build();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        final File file = new File(photo.getName());
        FileUtils.writeByteArrayToFile(file, photo.getBytes());
        final HttpPost httppost = new HttpPost("http://todo100.ru:18080/static/upload");
        builder.addPart("image", new FileBody(file, ContentType.create(photo.getContentType())));
        httppost.setEntity(builder.build());
        final HttpResponse response = httpclient.execute(httppost);
        final StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        final String theString = writer.toString();


        PhotoItem photo1 = new PhotoItem();
        photo1.setPath(theString);
        photo1.setMiddlePath(theString);
        photo1.setSmallPath(theString);


        photo1.setAccountId(accountService.getCurrentAccount().getId());
        photo1.setAlbumId(album);

        photosDao.save(photo1);

        return "redirect:/photos/album" + album;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add( final HttpServletRequest request,Model model) {
        final PhotoForm photoForm = new PhotoForm();
        try {
            Integer album = Integer.parseInt(request.getParameter("album"));
            photoForm.setAlbum(album);
        } catch(Exception ignored){}

        model.addAttribute("photoForm", photoForm);

        return "photos/add";
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
}
