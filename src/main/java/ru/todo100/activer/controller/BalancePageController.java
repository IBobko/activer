package ru.todo100.activer.controller;

import com.paypal.api.payments.*;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.BalanceDao;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/balance")
public class BalancePageController {

    @Autowired
    BalanceDao balanceDao;

    @Autowired
    AccountDao accountService;

    private PayPalService payPalService;

    public PayPalService getPayPalService() {
        return payPalService;
    }

    @Autowired
    public void setPayPalService(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @RequestMapping()
    public String index(Model model, HttpServletRequest request) throws IOException {
        BalanceItem balance = balanceDao.createOrGet(accountService.getCurrentAccount());


        model.addAttribute("balance",balance);
        return "balance/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(HttpServletRequest req, HttpServletResponse resp) {
        final APIContext apiContext = getPayPalService().getApiContext();
        if (apiContext == null) return null;

        req.getParameter("sum");

        BigDecimal sum = new BigDecimal(req.getParameter("sum"));


        req.getSession().setAttribute("sum",sum);


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
        transaction.setDescription("This is the payment transaction description.");

        Item item = new Item();
        item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice(sum.toString());
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);


        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();


        String guid = UUID.randomUUID().toString().replaceAll("-", "");

        redirectUrls.setCancelUrl(getPayPalService().getServerName(req) + "/balance/cancel?guid=" + guid);
        redirectUrls.setReturnUrl(getPayPalService().getServerName(req) + "/balance/result/?guid=" + guid);

        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = null;
        try {
            createdPayment = payment.create(apiContext);
//                LOGGER.info("Created payment with id = "
//                        + createdPayment.getId() + " and status = "
//                        + createdPayment.getState());

            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    req.setAttribute("redirectURL", link.getHref());
                }
            }
            ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), Payment.getLastResponse(), null);

        } catch (PayPalRESTException e) {
            ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
        }


        JSONObject josn = new JSONObject(createdPayment);

        String href = josn.getJSONArray("links").getJSONObject(1).getString("href");


        return new ModelAndView("redirect:" + href);

    }

    @RequestMapping("/result")
    public ModelAndView result(final HttpServletRequest request) {

        BigDecimal sum = (BigDecimal)request.getSession().getAttribute("sum");
        BalanceItem balance = balanceDao.createOrGet(accountService.getCurrentAccount());
        balance.setSum(balance.getSum().add(sum));
        balanceDao.save(balance);
        return new ModelAndView("redirect:/balance");
    }
}
