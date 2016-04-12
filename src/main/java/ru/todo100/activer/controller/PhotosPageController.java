package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoAlbumDao;
import ru.todo100.activer.form.PhotoAlbumForm;
import ru.todo100.activer.model.PhotoAlbumItem;

import javax.servlet.http.HttpServletRequest;
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


    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping("/{id}")
    public String another() {
        return "photos/another";
    }

    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping("/{id}/photo-{photo}")
    public String photo() {
        return "photos/photo";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() {
        return "photos/add";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload() {
        return "redirect:/photos";
    }
}
