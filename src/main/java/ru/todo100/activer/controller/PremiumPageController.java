package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AuthorityItem;
import ru.todo100.activer.service.BalanceService;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/premium")
public class PremiumPageController {
    private static final String ROLE_FOR_BUYING = "ROLE_PARTNER";
    private BalanceService balanceService;
    private AccountDao accountService;
    @Value("${premiumAccount.price}")
    private BigDecimal PREMIUM_PRICE;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("premiumPrice", PREMIUM_PRICE);

        return "premium/index";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy() {
        final AccountItem accountItem = getAccountService().getCurrentAccount();

        boolean alreadyHasPartnerPrivileges = false;
        for (AuthorityItem item : accountItem.getAuthorities()) {
            if (item.getRole().equals(ROLE_FOR_BUYING)) {
                alreadyHasPartnerPrivileges = true;
            }
        }

        if (alreadyHasPartnerPrivileges) {
            return "redirect:/";
        }

        final BigDecimal accountBalance = accountItem.getBalance().getSum();

        if (accountBalance.compareTo(PREMIUM_PRICE) == -1) {
            return "redirect:/";
        }

        final AuthorityItem item = new AuthorityItem();
        item.setRole(ROLE_FOR_BUYING);
        item.setAccount(accountItem);
        accountItem.getAuthorities().add(item);
        getAccountService().save(accountItem);
        getBalanceService().subtractAccountBalanceSum(accountItem.getId(), PREMIUM_PRICE, "Покупка премиум аккаунта");
        return "premium/success";
    }
}
