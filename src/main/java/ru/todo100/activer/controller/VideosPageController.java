package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.VideoDao;
import ru.todo100.activer.data.VideoData;
import ru.todo100.activer.form.VideoForm;
import ru.todo100.activer.model.VideoItem;
import ru.todo100.activer.populators.VideoPopulator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/videos")
public class VideosPageController {
    private static final Pattern YOUTUBE_LINK = Pattern.compile("^https://www.youtube.com/watch\\?v=(.+)$");
    private VideoPopulator videoPopulator;
    @Autowired
    private AccountDao accountService;
    @Autowired
    private VideoDao videoDao;

    public VideoPopulator getVideoPopulator() {
        return videoPopulator;
    }

    @Autowired
    public void setVideoPopulator(VideoPopulator videoPopulator) {
        this.videoPopulator = videoPopulator;
    }

    @RequestMapping
    public String index(final Model model, @RequestParam(required = false, defaultValue = "") String accountId, HttpSession session) {
        final Integer accountIdInt;
        if (accountId.isEmpty()) {
            accountIdInt = accountService.getCurrentProfileData(session).getId();
            return "redirect:/videos?accountId=" + accountIdInt;
        } else {
            try {
                accountIdInt = Integer.parseInt(accountId);
            } catch (NumberFormatException e) {
                model.addAttribute("error", "error");
                return "videos/index";
            }
        }
        model.addAttribute("pageType", "videos");
        final List<VideoData> videoDatas = new ArrayList<>();
        for (final VideoItem videoItem : videoDao.getVideosByAccount(accountIdInt)) {
            videoDatas.add(getVideoPopulator().populate(videoItem));
        }
        model.addAttribute("videos", videoDatas);
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

        Integer accountId = accountService.getCurrentAccount().getId();
        List<VideoItem> videos = videoDao.getVideosByAccount(accountId);
        accountService.addSynchronizer(accountId, "videos", videos);

        return "redirect:/videos";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String save(final VideoForm videoForm, final BindingResult bindingResult) {
        Matcher m = YOUTUBE_LINK.matcher(videoForm.getBody());
        if (m.matches()) {
            String id = m.group(1);
            String iframe = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" + id + "\" frameborder=\"0\" allowfullscreen></iframe>";
            VideoItem videoItem = new VideoItem();
            Integer accountId = accountService.getCurrentAccount().getId();
            videoItem.setAccountId(accountId);
            videoItem.setBody(iframe);
            videoItem.setDescription(videoForm.getDescription());
            videoDao.save(videoItem);

            List<VideoItem> videos = videoDao.getVideosByAccount(accountId);
            accountService.addSynchronizer(accountId, "videos", videos);
        }
        return "redirect:/videos";
    }

}
