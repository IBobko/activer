package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.VideoDao;
import ru.todo100.activer.form.VideoForm;
import ru.todo100.activer.model.VideoItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/videos")
public class VideosPageController {

    @Autowired
    private AccountDao accountService;

    @Autowired
    private VideoDao videoDao;

    @RequestMapping
    public String index(final Model model) {
        model.addAttribute("pageType", "videos");
        Integer accountId = accountService.getCurrentAccount().getId();
        List<VideoItem> videos = videoDao.getVideosByAccount(accountId);
        model.addAttribute("videos", videos);
        return "videos/index";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Model model) {
        model.addAttribute("pageType", "videos");
        model.addAttribute("videoForm", new VideoForm());
        return "videos/edit";
    }

    @RequestMapping(value = "/remove")
    public String remove(HttpServletRequest request) {
        videoDao.delete(Integer.valueOf(request.getParameter("id")));
        return "redirect:/videos";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String save(final VideoForm videoForm, final BindingResult bindingResult) {
        VideoItem videoItem = new VideoItem();
        Integer accountId = accountService.getCurrentAccount().getId();
        videoItem.setAccountId(accountId);
        videoItem.setBody(videoForm.getBody());
        videoItem.setDescription(videoForm.getDescription());
        videoDao.save(videoItem);

        return "redirect:/videos";
    }

}
