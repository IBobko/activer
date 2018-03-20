package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.FriendDao;
import ru.todo100.activer.data.FriendData;
import ru.todo100.activer.data.FriendsData;
import ru.todo100.activer.form.FriendSearchForm;
import ru.todo100.activer.qualifier.AccountQualifier;
import ru.todo100.activer.service.FriendsService;
import ru.todo100.activer.service.NotificationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/friend")
public class FriendsPageController {

    private AccountDao accountService;
    private NotificationService notificationService;
    private FriendsService friendsService;
    @Autowired
    private FriendDao friendDao;

    public AccountDao getAccountService() {
        return accountService;
    }
    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public FriendsService getFriendsService() {
        return friendsService;
    }

    @Autowired
    public void setFriendsService(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping(value = {"", "/in", "/out", "/search"})
    public String index(final Model model, final HttpServletRequest request, @ModelAttribute final FriendSearchForm friendSearchForm) {
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (pattern.contains("/in")) {
            model.addAttribute("listType", "in");
        }

        if (pattern.contains("/out")) {
            model.addAttribute("listType", "out");
        }

        if (pattern.contains("/search")) {
            model.addAttribute("listType", "search");

            AccountQualifier qualifier = new AccountQualifier();
            qualifier.setFriendSearchForm(friendSearchForm);
            final List<FriendData> searchResult = accountService.getByQualifier(qualifier);
            model.addAttribute("searchResult", searchResult);
        }

        model.addAttribute("pageType", "friends");
        final FriendsData friends = friendsService.getFriendData(request.getSession());
        model.addAttribute("friendData", friends);
        return "friend/index";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletRequest request) {
        friendsService.deleteFriend(id);
        friendsService.synchronize(request.getSession());
        return "redirect:/friend";
    }

    @RequestMapping(value = "/add/{id}")
    public String add(@PathVariable final Integer id, final HttpSession session) {
        getFriendsService().add(id);
        getFriendsService().synchronize(session);
        getNotificationService().addNotification(getAccountService().getCurrentAccount().getId(),id,null,null,0,"");
        return "redirect:/friend";
    }

    @RequestMapping(value = "/list/id{id}")
    public String list(@PathVariable final Integer id, Model model) {
        model.addAttribute("friends", friendDao.getFriends(id));
        return "friend/list";
    }


    @ResponseBody
    @RequestMapping(value = "/ajax")
    public FriendsData ajax(final HttpServletRequest request) {
        return friendsService.getFriendData(request.getSession());
    }
}
