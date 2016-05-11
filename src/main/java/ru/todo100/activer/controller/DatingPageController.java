package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.DisputeThemeDao;
import ru.todo100.activer.dao.HappenedDisputeDao;
import ru.todo100.activer.dao.HappenedFlirtDao;
import ru.todo100.activer.data.*;
import ru.todo100.activer.model.*;
import ru.todo100.activer.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/dating")
public class DatingPageController {
    @Autowired
    private SimpMessagingTemplate template;

    private AccountDao accountDao;
    private HappenedDisputeDao happenedDisputeDao;
    @Autowired
    private PhotoService photoService1;
    @Autowired
    private DisputeThemeDao disputeThemeDao;

    private HappenedFlirtDao happenedFlirtDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("pageType", "dating");
        try {
            Integer dialog = Integer.parseInt(request.getParameter("dialog"));
            model.addAttribute("dialog", dialog);
        } catch (NumberFormatException ignored) {
        }
        model.addAttribute("templatePost", MessageController.generateTemplateMessageData());
        return "dating/index";
    }

    @RequestMapping("/flirt")
    public String flirt(final HttpServletRequest request, final Model model, @RequestParam(defaultValue = "0") final Integer id) {
        if (id.equals(0)) {
            return "redirect:/dating";
        }
        final HappenedFlirtItem happenedFlirt = (HappenedFlirtItem) getHappenedFlirtDao().get(id);
        if (happenedFlirt != null) {
            ProfileData profileData = accountDao.getCurrentProfileData(request.getSession());
            Boolean init = null;

            if (profileData.getId().equals(happenedFlirt.getAccountAppliedId())) {
                init = false;
            }

            if (profileData.getId().equals(happenedFlirt.getAccountInitId())) {
                init = true;
            }

            if (init == null) {
                return "redirect:/dating";
            }

            model.addAttribute("pageType", "dating/flirt");

            final HappenedFlirtData flirtData = generateHappenedFlirtData(happenedFlirt, init);
            model.addAttribute("flirtData", flirtData);
            model.addAttribute("photo", photoService1.getPhoto(profileData.getId()));
            model.addAttribute("profile", profileData);
            return "dating/flirt";
        }
        return "redirect:/dating";
    }

    public HappenedFlirtData generateHappenedFlirtData(HappenedFlirtItem happenedFlirt, boolean init) {
        HappenedFlirtData happenedFlirtData = new HappenedFlirtData();
        happenedFlirtData.setId(happenedFlirt.getId());
        final AccountItem opponent;
        if (init) {
            opponent = accountDao.get(happenedFlirt.getAccountAppliedId());
        } else {
            opponent = accountDao.get(happenedFlirt.getAccountInitId());
        }

        final String photo = photoService1.getPhoto(opponent.getId());
        happenedFlirtData.setOpponentAvatar(photo);
        happenedFlirtData.setStartedDate(happenedFlirt.getStartedDate());
        happenedFlirtData.setBirthday(opponent.getBirthdate());

        happenedFlirtData.setOpponentFirstName(opponent.getFirstName());
        happenedFlirtData.setOpponentLastName(opponent.getLastName());

        Set<InterestItem> interests = opponent.getInterestItems();
        List<String> interestsData = new ArrayList<>();
        for (InterestItem item : interests) {
            interestsData.add(item.getName());
        }
        happenedFlirtData.setInterests(interestsData);
        return happenedFlirtData;
    }

    @Transactional
    public HappenedDisputeData generate(final HappenedDisputeItem item, final boolean init) {
        final HappenedDisputeData happenedDisputeData = new HappenedDisputeData();
        happenedDisputeData.setId(item.getId());

        final DisputeThemeItem disputeThemeItem = (DisputeThemeItem)disputeThemeDao.get(item.getThemeId());

        happenedDisputeData.setThemeTitle(disputeThemeItem.getName());
        final AccountItem opponent;
        if (init) {
            opponent = accountDao.get(item.getAccountAppliedId());
        } else {
            opponent = accountDao.get(item.getAccountInitId());
        }

        Integer position = item.getAppliedPosition();
        if (position == 0) {
            happenedDisputeData.setOpponentPosition(disputeThemeItem.getPosition1());
            happenedDisputeData.setYourPosition(disputeThemeItem.getPosition2());
        } else{
            happenedDisputeData.setOpponentPosition(disputeThemeItem.getPosition2());
            happenedDisputeData.setYourPosition(disputeThemeItem.getPosition1());

        }


        final String photo = photoService1.getPhoto(opponent.getId());

        happenedDisputeData.setOpponentAvatar(photo);
        happenedDisputeData.setOpponentFistName(opponent.getFirstName());
        happenedDisputeData.setOpponentLastName(opponent.getLastName());
        happenedDisputeData.setOpponentId(opponent.getId());


        return happenedDisputeData;
    }

    @RequestMapping(value = "/dispute")
    public String dispute(final HttpServletRequest request, final Model model, @RequestParam(defaultValue = "0") final Integer id) {
        if (id.equals(0)) {
            return "redirect:/dating";
        }

        final HappenedDisputeItem happenedDispute = (HappenedDisputeItem) happenedDisputeDao.get(id);
        if (happenedDispute != null) {
            ProfileData profileData = accountDao.getCurrentProfileData(request.getSession());
            Boolean init = null;

            if (profileData.getId().equals(happenedDispute.getAccountAppliedId())) {
                init = false;
            }

            if (profileData.getId().equals(happenedDispute.getAccountInitId())) {
                init = true;
            }

            if (init == null) {
                return "redirect:/dating";
            }

            model.addAttribute("pageType", "dating/dispute");

            final HappenedDisputeData disputeData = generate(happenedDispute, init);

            model.addAttribute("disputeData", disputeData);
            model.addAttribute("photo", photoService1.getPhoto(profileData.getId()));
            model.addAttribute("profile",profileData);
            /*todo ИСПОРЧЕНО*/
            model.addAttribute("templatePost", MessageController.generateTemplateMessageData());
            return "dating/dispute";
        }
        return "redirect:/dating";
    }

    public HappenedDisputeDao getHappenedDisputeDao() {
        return happenedDisputeDao;
    }

    @Autowired
    public void setHappenedDisputeDao(HappenedDisputeDao happenedDisputeDao) {
        this.happenedDisputeDao = happenedDisputeDao;
    }

    @ResponseBody
    @RequestMapping("/search")
    public void search(HttpServletResponse response, HttpServletRequest request) throws IOException {
        final AccountItem account = getAccountDao().getRandomOnlineAccount(request.getSession());
        final ProfileData profileData = getAccountDao().getCurrentProfileData(request.getSession());
        final HappenedDisputeItem happenedDisputeItem = getHappenedDisputeDao().create(profileData.getId(), account.getId());
        final PacketMessageData messageData = new PacketMessageData();
        messageData.setType(PopupMessageType.DATING);
        final MessageAccountData from = new MessageAccountData();
        from.setId(account.getId());
        messageData.setFrom(from);
        template.convertAndSendToUser(account.getUsername(), "/global2", messageData);
        response.getWriter().println(happenedDisputeItem.getId());
    }

    public HappenedFlirtDao getHappenedFlirtDao() {
        return happenedFlirtDao;
    }

    @Autowired
    public void setHappenedFlirtDao(HappenedFlirtDao happenedFlirtDao) {
        this.happenedFlirtDao = happenedFlirtDao;
    }

    @ResponseBody
    @RequestMapping("/search/flirt")
    public void searchFlirt(HttpServletResponse response, HttpServletRequest request) throws IOException {
        final AccountItem account = getAccountDao().getRandomOnlineAccount(request.getSession());
        final ProfileData profileData = getAccountDao().getCurrentProfileData(request.getSession());
        final HappenedFlirtItem happenedFlirtItem = getHappenedFlirtDao().create(profileData.getId(), account.getId());
        final PacketMessageData messageData = new PacketMessageData();
        messageData.setType(PopupMessageType.FLIRT);
        final MessageAccountData from = new MessageAccountData();
        from.setId(account.getId());
        messageData.setFrom(from);
        template.convertAndSendToUser(account.getUsername(), "/global2", messageData);
        response.getWriter().println(happenedFlirtItem.getId());
    }
}
