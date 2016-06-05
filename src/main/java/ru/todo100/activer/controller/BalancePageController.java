package ru.todo100.activer.controller;

import com.paypal.api.payments.*;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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


    private PayPalService payPalService;

    public PayPalService getPayPalService() {
        return payPalService;
    }

    @Autowired
    public void setPayPalService(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @RequestMapping()
    public String index() {
        return "balance/index";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Payment post(HttpServletRequest req, HttpServletResponse resp) {
        final APIContext apiContext = getPayPalService().getApiContext();
        if (apiContext == null) return null;


        final Details details = new Details();
        details.setShipping("1");
        details.setSubtotal("90");
        details.setTax("1");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("92");
        amount.setDetails(details);


        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description.");

        Item item = new Item();
        item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("90");
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

        redirectUrls.setCancelUrl(getPayPalService().getServerName(req) + "/gifts/cancel?guid=" + guid);
        redirectUrls.setReturnUrl(getPayPalService().getServerName(req) + "/gifts/result?guid=" + guid);

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

        return createdPayment;

    }

    String token() throws Exception {
        InputStream is = getClass().getResourceAsStream("/sdk_config.properties");

        Properties prop = new Properties();
        prop.load(is);
        OAuthTokenCredential tokenCredential = Payment.initConfig(prop);
        String accessToken = tokenCredential.getAccessToken();
        return accessToken;
    }

}
