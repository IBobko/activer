package ru.todo100.activer.controller;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.BalanceDao;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.service.BalanceService;
import ru.todo100.activer.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

@SuppressWarnings("WeakerAccess")
class Pay implements Serializable {
    private BigDecimal sum;
    private String guid;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}

@SuppressWarnings("WeakerAccess")
@Controller
@RequestMapping("/balance")
public class BalancePageController {

    private BalanceDao balanceDao;
    private AccountDao accountService;
    private PayPalService payPalService;
    private BalanceService balanceService;

    public BalanceDao getBalanceDao() {
        return balanceDao;
    }

    @Autowired
    public void setBalanceDao(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public PayPalService getPayPalService() {
        return payPalService;
    }

    @Autowired
    public void setPayPalService(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @RequestMapping()
    public String index(final Model model) {
        final BalanceItem balance = getBalanceDao().createOrGet(getAccountService().getCurrentAccount());
        model.addAttribute("balance", balance);
        return "balance/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(final HttpServletRequest request) {
        final APIContext apiContext = getPayPalService().getApiContext();
        if (apiContext == null) return null;

        final BigDecimal sum = new BigDecimal(request.getParameter("sum"));


//        final Details details = new Details();
//        details.setShipping("1");
//        details.setSubtotal(sum.subtract(new BigDecimal("2")).toString());
//        details.setTax("1");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum.toString());
        //amount.setDetails(details);


        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        //transaction.setDescription("This is the payment transaction description.");

        Item item = new Item();
        item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice(sum.toString());
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);


        final List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();


        final String guid = UUID.randomUUID().toString().replaceAll("-", "");

        redirectUrls.setCancelUrl(getPayPalService().getServerName(request) + "/balance/cancel?guid=" + guid);
        redirectUrls.setReturnUrl(getPayPalService().getServerName(request) + "/balance/result/?guid=" + guid);
        payment.setRedirectUrls(redirectUrls);

        String href = "/balance";
        try {
            Payment createdPayment = payment.create(apiContext);
            for (final Links link : createdPayment.getLinks()) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    href = link.getHref();
                    break;
                }
            }
        } catch (PayPalRESTException ignored) {
        }

        final Pay pay = new Pay();
        pay.setGuid(guid);
        pay.setSum(sum);
        request.getSession().setAttribute("pay", pay);

        return new ModelAndView("redirect:" + href);
    }

    @RequestMapping("/cancel")
    public ModelAndView cancel(final HttpServletRequest request) {
        final Pay pay = (Pay) request.getSession().getAttribute("pay");
        final String guid = request.getParameter("guid");
        if (pay != null && guid.equals(pay.getGuid())) {
            request.getSession().removeAttribute("pay");
        }
        return new ModelAndView("redirect:/balance");
    }

    public BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @RequestMapping("/result")
    public ModelAndView result(final HttpServletRequest request) {
        final Pay pay = (Pay) request.getSession().getAttribute("pay");
        final String guid = request.getParameter("guid");
        if (pay != null && guid.equals(pay.getGuid())) {
            final ProfileData profileData = getAccountService().getCurrentProfileData(request.getSession());
            getBalanceService().additionAccountBalanceSum(profileData.getId(), pay.getSum(), "Поступление средств через PayPal");
            request.getSession().removeAttribute("pay");
        }
        return new ModelAndView("redirect:/balance");
    }
}
