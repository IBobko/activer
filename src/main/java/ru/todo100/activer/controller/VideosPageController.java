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
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.form.VideoForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.VideoItem;
import ru.todo100.activer.populators.ProfilePopulator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private AccountDao accountService;
    private VideoDao videoDao;
    private ProfilePopulator profilePopulator;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }


    public VideoDao getVideoDao() {
        return videoDao;
    }

    @Autowired
    public void setVideoDao(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    public ProfilePopulator getProfilePopulator() {

        return profilePopulator;
    }

    @Autowired
    public void setProfilePopulator(ProfilePopulator profilePopulator) {
        this.profilePopulator = profilePopulator;
    }

    @RequestMapping
    public String index(final Model model, @RequestParam(required = false, defaultValue = "") String accountId, HttpSession session) {
        final Integer accountIdInt;
        if (accountId.isEmpty()) {
            accountIdInt = getAccountService().getCurrentProfileData(session).getId();
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
        if (getAccountService().getCurrentProfileData(session).getId().equals(accountIdInt)) {
            model.addAttribute("profile", getAccountService().getCurrentProfileData(session));
        } else {
            AccountItem accountItem = getAccountService().get(accountIdInt);
            ProfileData profileData = getProfilePopulator().populate(accountItem);
            model.addAttribute("profile", profileData);
        }
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

        Integer accountId = getAccountService().getCurrentAccount().getId();
        List<VideoItem> videos = getVideoDao().getVideosByAccount(accountId);
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
            Integer accountId = getAccountService().getCurrentAccount().getId();
            videoItem.setAccountId(accountId);
            videoItem.setBody(iframe);
            videoItem.setDescription(videoForm.getDescription());
            getVideoDao().save(videoItem);

            List<VideoItem> videos = getVideoDao().getVideosByAccount(accountId);
            getAccountService().addSynchronizer(accountId, "videos", videos);
        }
        return "redirect:/videos";
    }

}
