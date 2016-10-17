package ru.todo100.activer.controller;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.exception.*;
import com.paypal.sdk.exceptions.OAuthException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/paypal")
public class PayPalController {

    private static final Logger LOGGER = Logger
            .getLogger(PayPalController.class);
    Map<String, String> map = new HashMap<String, String>();
    @Autowired
    private AccountDao accountService;
    @Autowired
    private PaymentServiceImpl paymentService;

    @RequestMapping
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        JSONObject josn = new JSONObject(createPayment(req,resp));

        String href = josn.getJSONArray("links").getJSONObject(1).getString("href");


        return new ModelAndView("redirect:" + href);


    }


    String token() throws Exception {
        InputStream is = getClass().getResourceAsStream("/sdk_config.properties");

        Properties prop = new Properties();
        prop.load(is);
        OAuthTokenCredential tokenCredential = Payment.initConfig(prop);
        String accessToken = tokenCredential.getAccessToken();
        return accessToken;
    }

    public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Payment createdPayment = null;


        APIContext apiContext = null;
        String accessToken = null;
        try {







            accessToken = token();
            apiContext = new APIContext(accessToken);

        } catch (PayPalRESTException e) {
            req.setAttribute("error", e.getMessage());
        }
        if (req.getParameter("PayerID") != null) {
            Payment payment = new Payment();
            if (req.getParameter("guid") != null) {
                payment.setId(map.get(req.getParameter("guid")));
            }

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(req.getParameter("PayerID"));
            try {
                createdPayment = payment.execute(apiContext, paymentExecution);
                //ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), Payment.getLastResponse(), null);
            } catch (PayPalRESTException e) {
                //ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), null, e.getMessage());
            }
        } else {

            Details details = new Details();
            details.setShipping("1");
            details.setSubtotal("5");
            details.setTax("1");

            Amount amount = new Amount();
            amount.setCurrency("USD");
            amount.setTotal("7");
            amount.setDetails(details);



            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction
                    .setDescription("This is the payment transaction description.");

            Item item = new Item();
            item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("5");
            ItemList itemList = new ItemList();
            List<Item> items = new ArrayList<Item>();
            items.add(item);
            itemList.setItems(items);

            transaction.setItemList(itemList);



            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);

            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            RedirectUrls redirectUrls = new RedirectUrls();

            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paypal/calcel?guid=" + guid);
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paypal/return?guid=" + guid);
            payment.setRedirectUrls(redirectUrls);



            try {
                createdPayment = payment.create(apiContext);
                LOGGER.info("Created payment with id = "
                        + createdPayment.getId() + " and status = "
                        + createdPayment.getState());

                Iterator<Links> links = createdPayment.getLinks().iterator();
                while (links.hasNext()) {
                    Links link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        req.setAttribute("redirectURL", link.getHref());
                    }
                }
                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), Payment.getLastResponse(), null);
                map.put(guid, createdPayment.getId());
            } catch (PayPalRESTException e) {
                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
            }
        }
        return createdPayment;
    }


    @RequestMapping("/cancel")
    @ResponseBody
    public String cancel(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return "cancel";
    }

    @RequestMapping("/return")

    public String result(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        AccountItem accountItem = accountService.getCurrentAccount();
        try {
            accountItem.addRole("ROLE_PARTNER");
            accountService.save(accountItem);
        } catch(Exception ignored) {

        }
        paymentService.addPayment(accountItem.getId(),new BigDecimal(50));


        return "paypal/success";
    }


    @RequestMapping("/out")
    @ResponseBody
    public PayoutBatch out(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      return createSynchronousSinglePayout(req,resp);
    }


    @SuppressWarnings("unchecked")
    public PayoutBatch createSynchronousSinglePayout(HttpServletRequest req,
                                                     HttpServletResponse resp) throws Exception {

        Payout payout = new Payout();

        PayoutSenderBatchHeader senderBatchHeader = new PayoutSenderBatchHeader();

        Random random = new Random();
        senderBatchHeader.setSenderBatchId(
                new Double(random.nextDouble()).toString()).setEmailSubject(
                "You have a Payout!");

        Currency amount = new Currency();
        amount.setValue("10").setCurrency("USD");

        PayoutItem senderItem = new PayoutItem();
        senderItem.setRecipientType("Email")
                .setNote("Thanks for your patronage")
                .setReceiver("limit-speed@yandex.ru")
                .setSenderItemId("201404324234").setAmount(amount);

        List<PayoutItem> items = new ArrayList<PayoutItem>();
        items.add(senderItem);

        payout.setSenderBatchHeader(senderBatchHeader).setItems(items);

        PayoutBatch batch = null;
        try {

            String accessToken = token();

            APIContext apiContext = new APIContext(accessToken);

                    batch = payout.createSynchronous(apiContext);

            LOGGER.info("Payout Batch With ID: "
                    + batch.getBatchHeader().getPayoutBatchId());
            ResultPrinter.addResult(req, resp,
                    "Created Single Synchronous Payout",
                    Payout.getLastRequest(), Payout.getLastResponse(), null);
        } catch (PayPalRESTException e) {
            ResultPrinter.addResult(req, resp,
                    "Created Single Synchronous Payout",
                    Payout.getLastRequest(), null, e.getMessage());
        }
        return batch;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String createAccount() throws IOException, OAuthException, InvalidResponseDataException, SSLConfigurationException, MissingCredentialException, HttpErrorException, InvalidCredentialException, ClientActionRequiredException, InterruptedException {
//
//        RequestEnvelope env = new RequestEnvelope();
//        env.setErrorLanguage("en_US");
//
//        NameType name = new NameType("John", "Lui");
//
//
//        AddressType address = new AddressType("Main St", "US");
//
//        String preferredLanguageCode = "en_US";
//
//        CreateAccountRequest createAccountRequest = new CreateAccountRequest(env,
//                name, address, preferredLanguageCode);
//
//        Map<String, String> customConfigurationMap = new HashMap<String, String>();
//        customConfigurationMap.put("mode", "sandbox"); // Load the map with all mandatory parameters
//        customConfigurationMap.put("acct1.UserName", "limit-speed_api2.ya.ru");
//        customConfigurationMap.put("acct1.Password", "J9HQTXEEYPMX9FCR");
//        customConfigurationMap.put("acct1.Signature", "AFcWxV21C7fd0v3bYYYRCpSSRl31AEwgUxCEukmIx.B4rWL3njNJmCNL");
//        customConfigurationMap.put("acct1.AppId", "APP-80W284485P519543T");
//        customConfigurationMap.put("acct1.Subject", "sandbox");
//
//        customConfigurationMap.put("X-PAYPAL-SANDBOX-EMAIL-ADDRESS", "limit-speed@yandex.ua");
//
//
//        AdaptiveAccountsService adaptiveAccountsService = new AdaptiveAccountsService(customConfigurationMap);
//
//
//        CreateAccountResponse createAccountResponse = adaptiveAccountsService.createAccount(createAccountRequest, "limit-speed@yandex.ua");
//
//
//        return "DONE" + createAccountResponse.getAccountId();
    return null;
    }

}
