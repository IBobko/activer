package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.TopLineDao;
import ru.todo100.activer.service.BalanceService;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/top-line")
public class TopLineController {
    private BalanceService balanceService;
    private AccountDao accountService;
    private TopLineDao topLineDao;

    public BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public TopLineDao getTopLineDao() {
        return topLineDao;
    }

    @Autowired
    public void setTopLineDao(TopLineDao topLineDao) {
        this.topLineDao = topLineDao;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("pageType", "profile/dating");
        return "topline/index";
    }

    @RequestMapping(value = "/buy")
    public String buy(@RequestParam final String message) {
        final Integer account_id = getAccountService().getCurrentAccount().getId();
        if (getBalanceService().subtractAccountBalanceSum(account_id,new BigDecimal(1),"Top")) {
            getTopLineDao().addTopLine(account_id,message);
        }
        return "redirect:/dating";
    }

}
