package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.payeer.domain.PayeerForm;
import ru.todo100.activer.payeer.service.PayeerService;
import ru.todo100.activer.service.BalanceService;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */


@Controller
@RequestMapping("/balance")
public class BalancePageController {
    private AccountDao accountService;
    private BalanceService balanceService;
    private PayeerService payeerService;

    public PayeerService getPayeerService() {
        return payeerService;
    }

    @Autowired
    public void setPayeerService(PayeerService payeerService) {
        this.payeerService = payeerService;
    }

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

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(final HttpServletRequest request, final Model model) {
        final String sum = request.getParameter("sum");
        if (sum == null) {
            return new ModelAndView("redict:/balance");
        }

        final PayeerForm form = new PayeerForm();
        final NumberFormat numberFormatter = new DecimalFormat("0.00");
        form.setShop(getPayeerService().getShop());
        form.setKey(getPayeerService().getKey());
        form.setOrder(1);
        form.setAmount(numberFormatter.format(Double.parseDouble(sum)));
        form.setDesc(getAccountService().getCurrentProfileData(request.getSession()).getId().toString());
        form.setCurr(getPayeerService().getDefaultCurr());
        form.setSign(getPayeerService().getSign(form));
        model.addAttribute("payeer", form);
        return new ModelAndView("balance/payeer_submit", model.asMap());
    }

    private BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

}
