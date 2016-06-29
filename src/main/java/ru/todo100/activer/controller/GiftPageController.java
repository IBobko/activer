package ru.todo100.activer.controller;

import com.paypal.api.payments.*;
import com.paypal.api.payments.util.ResultPrinter;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import ru.todo100.activer.service.PayPalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/gifts")
public class GiftPageController {
    private AccountDao accountService;
    private AccountGiftDao accountGiftDao;
    private PayPalService payPalService;
    private AccountGiftDataPopulator accountGiftDataPopulator;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public AccountGiftDao getAccountGiftDao() {
        return accountGiftDao;
    }

    @Autowired
    public void setAccountGiftDao(AccountGiftDao accountGiftDao) {
        this.accountGiftDao = accountGiftDao;
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        final ProfileData account = getAccountService().getCurrentProfileData(request.getSession());
        return "redirect:/gifts/id" + account.getId();
    }

    public PayPalService getPayPalService() {
        return payPalService;
    }

    @Autowired
    public void setPayPalService(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @RequestMapping("/give")
    public ModelAndView give(final HttpServletRequest request, final HttpServletResponse response, @RequestParam(required = false, defaultValue = "0") Integer gift_id, @RequestParam(required = false, defaultValue = "0") Integer account_id) throws Exception {

        request.getSession().setAttribute("GIFT_FOR_BUY", gift_id);
        request.getSession().setAttribute("GIFT_FOR_ACCOUNT", account_id);
        final JSONObject json = new JSONObject(createPayment(request, response));
        String href = json.getJSONArray("links").getJSONObject(1).getString("href");
        return new ModelAndView("redirect:" + href);
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

    public AccountGiftDataPopulator getAccountGiftDataPopulator() {
        return accountGiftDataPopulator;
    }

    @Autowired
    public void setAccountGiftDataPopulator(AccountGiftDataPopulator accountGiftDataPopulator) {
        this.accountGiftDataPopulator = accountGiftDataPopulator;
    }

    @RequestMapping("/id{id}")
    @Transactional
    public String show(final Model model, @PathVariable final Integer id) {
        model.addAttribute("pageType", "profile/gifts/index");
        final List<AccountGiftItem> gifts = accountGiftDao.getGiftsByAccount(id);
        final List<AccountGiftData> giftData = new ArrayList<>();
        for (AccountGiftItem accountGiftItem : gifts) {
            giftData.add(getAccountGiftDataPopulator().populate(accountGiftItem));
        }
        model.addAttribute("gifts", giftData);
        return "gifts/index";
    }

    public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) throws Exception {

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
}
