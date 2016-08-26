package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.VideoDao;
import ru.todo100.activer.form.VideoForm;
import ru.todo100.activer.model.VideoItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/videos")
public class VideosPageController {
    private static final Pattern YOUTUBE_LINK= Pattern.compile("^https://www.youtube.com/watch\\?v=(.+)$");

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
        Matcher m = YOUTUBE_LINK.matcher( videoForm.getBody());
        if (m.matches()) {
            String id = m.group(1);
            String iframe = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"+id+"\" frameborder=\"0\" allowfullscreen></iframe>";
            VideoItem videoItem = new VideoItem();
            Integer accountId = accountService.getCurrentAccount().getId();
            videoItem.setAccountId(accountId);
            videoItem.setBody(iframe);
            videoItem.setDescription(videoForm.getDescription());
            videoDao.save(videoItem);
        }
        return "redirect:/videos";
    }

}
