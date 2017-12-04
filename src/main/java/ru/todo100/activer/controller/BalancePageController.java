package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.service.BalanceService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */


@Controller
@RequestMapping("/balance")
public class BalancePageController {
    private AccountDao accountService;
    private BalanceService balanceService;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @RequestMapping
    public String index(final Model model) {
        final BalanceItem balance = getBalanceService().getBalance(getAccountService().getCurrentAccount());
        model.addAttribute("balance", balance);
        return "balance/index";
    }

    private BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

}
