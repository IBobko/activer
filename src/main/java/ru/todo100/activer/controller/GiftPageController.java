package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.AccountGiftDao;
import ru.todo100.activer.data.AccountGiftData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountGiftItem;
import ru.todo100.activer.populators.AccountGiftDataPopulator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/gifts")
public class GiftPageController {
    private AccountDao accountService;
    private AccountGiftDao accountGiftDao;
    private AccountGiftDataPopulator accountGiftDataPopulator;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    private AccountGiftDao getAccountGiftDao() {
        return accountGiftDao;
    }

    @Autowired
    public void setAccountGiftDao(AccountGiftDao accountGiftDao) {
        this.accountGiftDao = accountGiftDao;
    }

    @RequestMapping
    public String index(final HttpSession session) {
        final ProfileData account = getAccountService().getCurrentProfileData(session);
        return "redirect:/gifts/id" + account.getId();
    }

    @RequestMapping("/give")
    public ModelAndView give(final HttpServletRequest request, @RequestParam(required = false, defaultValue = "0") Integer gift_id, @RequestParam(required = false, defaultValue = "0") Integer account_id) {
        request.getSession().setAttribute("GIFT_FOR_BUY", gift_id);
        request.getSession().setAttribute("GIFT_FOR_ACCOUNT", account_id);
        return new ModelAndView("redirect:/");
    }

    @ResponseBody
    @RequestMapping("/result")
    public String result(HttpServletRequest request) {
        Integer gift_id = (Integer) request.getSession().getAttribute("GIFT_FOR_BUY");
        Integer account_id = (Integer) request.getSession().getAttribute("GIFT_FOR_ACCOUNT");
        ProfileData account = getAccountService().getCurrentProfileData(request.getSession());
        getAccountGiftDao().give(account.getId(), account_id, gift_id, "Дарю тебе от всего сердца столь дорогую вещь");
        return "Спасибо за покупку подарка " + gift_id + " для " + account_id;
    }

    @ResponseBody
    @RequestMapping("/cancel")
    public String cancel(HttpServletRequest request) {
        Integer gift_id = (Integer) request.getSession().getAttribute("GIFT_FOR_BUY");
        Integer account_id = (Integer) request.getSession().getAttribute("GIFT_FOR_ACCOUNT");
        return "Отмена покупки " + gift_id + " для " + account_id;
    }

    private AccountGiftDataPopulator getAccountGiftDataPopulator() {
        return accountGiftDataPopulator;
    }

    @Autowired
    public void setAccountGiftDataPopulator(AccountGiftDataPopulator accountGiftDataPopulator) {
        this.accountGiftDataPopulator = accountGiftDataPopulator;
    }

    @RequestMapping("/id{id}")
    public String show(final Model model, @PathVariable final Integer id) {
        model.addAttribute("pageType", "profile/gifts/index");
        final List<AccountGiftItem> gifts = getAccountGiftDao().getGiftsByAccount(id);
        final List<AccountGiftData> giftData = new ArrayList<>();
        for (final AccountGiftItem accountGiftItem : gifts) {
            giftData.add(getAccountGiftDataPopulator().populate(accountGiftItem));
        }
        model.addAttribute("gifts", giftData);
        return "gifts/index";
    }
}
