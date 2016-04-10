package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.form.PhotoAlbumForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/photos")
public class PhotosPageController {
    @RequestMapping("")
    public String index(final Model model) {
        model.addAttribute("pageType","photos");
        return "photos/index";
    }

    @RequestMapping("/edit")
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



    @RequestMapping("/{id}")
    public String another() {
        return "photos/another";
    }

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
