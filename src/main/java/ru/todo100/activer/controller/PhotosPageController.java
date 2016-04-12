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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoAlbumDao;
import ru.todo100.activer.dao.PhotosDao;
import ru.todo100.activer.form.PhotoAlbumForm;
import ru.todo100.activer.model.PhotoAlbumItem;
import ru.todo100.activer.model.PhotoItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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
        Integer albumId = null;
        try {
            albumId = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException ignored){}

        if (albumId == null) {
            model.addAttribute("photoAlbumForm",new PhotoAlbumForm());
        }

        return "photos/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postAlbum(PhotoAlbumForm photoAlbumForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final PhotoAlbumItem photoAlbumItem;
            if (photoAlbumForm.getId() != null) {
                photoAlbumItem = photoAlbumDao.getSession().load(PhotoAlbumItem.class, photoAlbumForm.getId());
            } else {
                photoAlbumItem = new PhotoAlbumItem();
            }

            photoAlbumItem.setName(photoAlbumForm.getName());
            photoAlbumItem.setDescription(photoAlbumForm.getName());
            photoAlbumItem.setAccountId(accountService.getCurrentAccount().getId());
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
    public String upload(@RequestParam(value = "photo", required = false) MultipartFile photo,@RequestParam(value = "album", required = false)Integer album) throws IOException {
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
        try {
            Integer album = Integer.parseInt(request.getParameter("album"));
            model.addAttribute("album",album);
        } catch(Exception ignored){}
        return "photos/add";
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping("/{id}/photo-{photo}")
    public String photo() {
        return "photos/photo";
    }




}
