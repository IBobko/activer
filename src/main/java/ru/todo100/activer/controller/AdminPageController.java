package ru.todo100.activer.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.GiftCategoryDao;
import ru.todo100.activer.dao.GiftDao;
import ru.todo100.activer.dao.PaymentCreditDao;
import ru.todo100.activer.data.*;
import ru.todo100.activer.form.DisputeThemeForm;
import ru.todo100.activer.form.GiftAddForm;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.model.DisputeThemeItem;
import ru.todo100.activer.model.GiftCategoryItem;
import ru.todo100.activer.model.GiftItem;
import ru.todo100.activer.qualifier.DisputeThemeQualifier;
import ru.todo100.activer.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Value("${admin.partner.perpage}")
    private Integer COUNT_PER_PAGE;
    private GiftService giftService;
    private AdminAccountService adminAccountService;
    @Autowired
    private AccountDao accountService;
    private PartnerService partnerService;
    @Autowired
    private SimpMessagingTemplate template;

    private DisputeService disputeService;
    @Autowired
    private GiftCategoryDao giftCategoryDao;
    @Autowired
    private GiftDao giftDao;
    private PaymentService paymentService;
    @Autowired
    private PaymentCreditDao paymentCreditDao;

    public DisputeService getDisputeService() {
        return disputeService;
    }

    @Autowired
    public void setDisputeService(DisputeService disputeService) {
        this.disputeService = disputeService;
    }

    public GiftService getGiftService() {
        return giftService;
    }

    @Autowired
    public void setGiftService(GiftService giftService) {
        this.giftService = giftService;
    }

    public AdminAccountService getAdminAccountService() {
        return adminAccountService;
    }

    @Autowired
    public void setAdminAccountService(AdminAccountService adminAccountService) {
        this.adminAccountService = adminAccountService;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public PartnerService getPartnerService() {
        return partnerService;
    }

    @Autowired
    public void setPartnerService(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @RequestMapping("/creator")
    public String creator(final Model model, final PagedForm pagedForm, @RequestParam(name = "synch", required = false) Integer synch) {
        if (synch != null) {
            getAdminAccountService().synchronize();
        }
        model.addAttribute("pageType", "admin/creator");

        model.addAttribute("pagedData", adminAccountPagedData(pagedForm));
        return "admin/creator";
    }

    public PagedData<GiftItem> getGiftPageData(final PagedForm pagedForm) {
        Qualifier qualifier = new Qualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        if (pagedForm.getOrderType() != null && pagedForm.getOrderField() != null) {
            qualifier.setOrderName(pagedForm.getOrderField());
            qualifier.setOrder(Qualifier.Order.valueOf(pagedForm.getOrderType()));
        }

        final Long count = giftDao.getCountByQualifier(qualifier);
        final List<GiftItem> giftItems = giftDao.getGiftsByQualifier(qualifier);
        final PagedData<GiftItem> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count * 1.0 / COUNT_PER_PAGE));
        pagedData.setElements(giftItems);
        return pagedData;
    }

    public PagedData<DisputeThemeData> getDisputePageData(final PagedForm pagedForm) {
        final DisputeThemeQualifier qualifier = new DisputeThemeQualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        if (pagedForm.getOrderType() != null && pagedForm.getOrderField() != null) {
            qualifier.setOrderName(pagedForm.getOrderField());
            qualifier.setOrder(Qualifier.Order.valueOf(pagedForm.getOrderType()));
        }
        final Long count = getDisputeService().getCountByQualifier(qualifier);
        final List<DisputeThemeData> disputes = getDisputeService().getDataByQualifier(qualifier);
        final PagedData<DisputeThemeData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count * 1.0 / COUNT_PER_PAGE));
        pagedData.setElements(disputes);
        return pagedData;
    }

    @RequestMapping("/gifts")
    public String gifts(final Model model, final PagedForm pagedForm) {
        model.addAttribute("pageType", "admin/gifts");
        model.addAttribute("pagedData", getGiftPageData(pagedForm));
        return "admin/gifts";
    }

    @RequestMapping("/gifts/add")
    public String giftsAdd(final Model model,@RequestParam(required = false, defaultValue = "0") Integer id) {
        model.addAttribute("pageType", "admin/gifts/add");
        GiftAddForm giftAddForm = new GiftAddForm();
        if (!id.equals(0)) {
            final Session session = giftDao.getSessionFactory().openSession();
            final GiftItem giftItem = (GiftItem)session.get(giftDao.getItemClass(),id);

            giftAddForm.setId(giftItem.getId());
            giftAddForm.setCategory(giftItem.getCategory());
            giftAddForm.setDescription(giftItem.getName());
            giftAddForm.setFileName(giftItem.getFile());
            giftAddForm.setEnabled(giftItem.getEnabled());

        }
        model.addAttribute("giftAddForm", giftAddForm);
        model.addAttribute("categories",giftCategoryDao.getCategories());
        return "admin/gifts/add";
    }

    @RequestMapping(value = "/gifts/category", method = RequestMethod.GET)
    public String giftsCategory(final Model model) {
        model.addAttribute("pageType", "admin/gifts/category");


        return "admin/gift/category";
    }

    @RequestMapping(value = "/gifts/category", method = RequestMethod.POST)
    public String giftsCategorySave(HttpServletRequest request) {
        Session session = giftDao.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String categoryName = request.getParameter("categoryName");

        GiftCategoryItem giftCategoryItem = new GiftCategoryItem();
        giftCategoryItem.setName(categoryName);

        session.save(giftCategoryItem);
        tx.commit();
        return "redirect:/admin/gifts";
    }

    @Transactional
    @RequestMapping("/gifts/upload")
    public String giftsUpload(final GiftAddForm giftAddForm) throws IOException {
        final HttpClient httpclient = HttpClientBuilder.create().build();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        final File file = new File(giftAddForm.getPhoto().getName());
        FileUtils.writeByteArrayToFile(file, giftAddForm.getPhoto().getBytes());
        final HttpPost httppost = new HttpPost("http://192.168.1.65:18080/static/upload");
        builder.addPart("image", new FileBody(file, ContentType.create(giftAddForm.getPhoto().getContentType())));
        httppost.setEntity(builder.build());
        final HttpResponse response = httpclient.execute(httppost);
        final StringWriter writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
        final String theString = writer.toString();


        GiftItem giftItem = new GiftItem();
        if (giftAddForm.getId()!=null) {
            final Session session = giftDao.getSessionFactory().openSession();
            final Transaction tx = session.beginTransaction();
            giftItem = (GiftItem)session.get(giftDao.getItemClass(),giftAddForm.getId());
            tx.commit();
        }

        giftItem.setFile(theString);
        giftItem.setName(giftAddForm.getDescription());
        giftItem.setCategory(giftAddForm.getCategory());
        giftItem.setEnabled(giftAddForm.getEnabled());
        Session session = giftDao.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(giftItem);
        tx.commit();
        return "redirect:/admin/gifts";
    }

    @RequestMapping(value = "/dispute/upload", method = RequestMethod.POST)
    public String disputeUpload(final DisputeThemeForm form, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            getDisputeService().editDispute(form);
        }
        return "redirect:/admin/dispute";
    }

    @RequestMapping("/dispute")
    public String dispute(final Model model, final PagedForm pagedForm) {
        model.addAttribute("pageType", "admin/dispute");
        model.addAttribute("pagedData", getDisputePageData(pagedForm));
        return "admin/dispute";
    }

    @RequestMapping("/dispute/add")
    public String disputeAdd(final Model model, @RequestParam(required = false, defaultValue = "0") Integer id) {
        model.addAttribute("pageType", "admin/dispute/add");

        final DisputeThemeForm disputeThemeForm = new DisputeThemeForm();
        if (id != null) {
            DisputeThemeItem dispute = getDisputeService().get(id);
            if (dispute != null) {
                disputeThemeForm.setId(dispute.getId());
                disputeThemeForm.setName(dispute.getName());
                disputeThemeForm.setPosition1(dispute.getPosition1());
                disputeThemeForm.setPosition2(dispute.getPosition2());
            }
        }

        model.addAttribute("disputeThemeForm", disputeThemeForm);
        return "admin/dispute/add";
    }

    @RequestMapping("/dispute/delete")
    public String deleteDispute(final Model model, @RequestParam(required = false, defaultValue = "0") Integer id) {
        model.addAttribute("pageType", "admin/dispute/add");
        if (id != null) {
            getDisputeService().delete(id);
        }
        return "redirect:/admin/dispute";
    }

    public PagedData<AdminAccountData> adminAccountPagedData(final PagedForm pagedForm) {
        AdminAccountQualifier qualifier = new AdminAccountQualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);

        if (pagedForm.getOrderType() != null && pagedForm.getOrderField() != null) {
            qualifier.setOrderName(pagedForm.getOrderField());
            qualifier.setOrder(Qualifier.Order.valueOf(pagedForm.getOrderType()));
        }

        final Long count = getAdminAccountService().getAccountsCount(qualifier);
        final PagedData<AdminAccountData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count * 1.0 / COUNT_PER_PAGE));
        pagedData.setElements(getAdminAccountService().getAccounts(qualifier));
        return pagedData;
    }

    @RequestMapping("/partner")
    public String partner(final Model model, final PagedForm pagedForm, @RequestParam(name = "synch", required = false) Integer synch) {
        if (synch != null) {
            getPartnerService().synchronize(accountService.getCurrentAccount().getId());
        }
        model.addAttribute("pageType", "admin/partner");
        model.addAttribute("pagedData", pagedFormToPagedData(pagedForm));
        return "admin/partner";
    }

    @RequestMapping("/balance")
    public String balance(final Model model, final PagedForm pagedForm, @RequestParam(name = "synch", required = false) Integer synch) {
        if (synch != null) {
            getPartnerService().synchronize(accountService.getCurrentAccount().getId());
        }
        model.addAttribute("pageType", "admin/balance");
        model.addAttribute("pagedData", getBalanceForPagedForm(pagedForm));
        return "admin/balance";
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    private PagedData<BalanceData> getBalanceForPagedForm(PagedForm pagedForm) {
        final Integer accountId = accountService.getCurrentAccount().getId();
        final BalanceQualifier qualifier = new BalanceQualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);

        if (pagedForm.getOrderType() != null && pagedForm.getOrderField() != null) {
            qualifier.setOrderName(pagedForm.getOrderField());
            qualifier.setOrder(Qualifier.Order.valueOf(pagedForm.getOrderType()));
        }
        final Long count = getPaymentService().getBalanceCount(qualifier);
        final PagedData<BalanceData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count * 1.0 / COUNT_PER_PAGE));
        pagedData.setElements(getPaymentService().getBalances(qualifier));
        return pagedData;
    }

    @ResponseBody
    @RequestMapping("/balancePaged")
    public PagedData balancePaged(final PagedForm pagedForm) {
        return getBalanceForPagedForm(pagedForm);
    }

    @ResponseBody
    @RequestMapping("/partnerPaged")
    public PagedData partnerPaged(final PagedForm pagedForm) {
        return pagedFormToPagedData(pagedForm);
    }

    @ResponseBody
    @RequestMapping("/disputePaged")
    public PagedData disputePaged(final PagedForm pagedForm) {
        return getDisputePageData(pagedForm);
    }

    public PagedData pagedFormToPagedData(final PagedForm pagedForm) {
        final Integer accountId = accountService.getCurrentAccount().getId();
        final PartnerQualifier qualifier = new PartnerQualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        qualifier.setOwnerAccountId(accountId);
        if (pagedForm.getOrderType() != null && pagedForm.getOrderField() != null) {
            qualifier.setOrderName(pagedForm.getOrderField());
            qualifier.setOrder(Qualifier.Order.valueOf(pagedForm.getOrderType()));
        }
        final Long count = getPartnerService().getPartnersCount(qualifier);
        final PagedData<PartnerData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count * 1.0 / COUNT_PER_PAGE));
        pagedData.setElements(getPartnerService().getPartners(qualifier));
        return pagedData;
    }

    @ResponseBody
    @RequestMapping("/out")
    public String out() {

//        final List<AccountItem> accounts = accountService.getAll();
//        Session session = paymentCreditDao.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//
//        for (AccountItem account: accounts) {
//            PaymentCreditItem paymentCreditItem = new PaymentCreditItem();
//            paymentCreditItem.setAccount(account);
//            paymentCreditItem.setPaymentCreditDescription("Оплатить партнерский аккаунт");
//            paymentCreditItem.setPaymentCreditSum(new BigDecimal("50"));
//            session.save(paymentCreditItem);
//        };
//        tx.commit();


        return "Done";
    }

}
