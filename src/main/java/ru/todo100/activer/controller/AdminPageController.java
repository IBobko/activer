package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.*;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.service.AdminAccountService;
import ru.todo100.activer.service.PartnerService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Value("${admin.partner.perpage}")
    private Integer COUNT_PER_PAGE;
    private AdminAccountService adminAccountService;
    @Autowired
    private AccountDao accountService;
    private PartnerService partnerService;
    @Autowired
    private SimpMessagingTemplate template;

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

    @RequestMapping("")
    public String admin(HttpServletRequest request) {


        String subscribe = request.getParameter("subscribe");

        template.convertAndSend(subscribe, "hello");

        return "admin/index";
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


    @ResponseBody
    @RequestMapping("/partnerPaged")
    public PagedData partnerPaged(final PagedForm pagedForm) {
        return pagedFormToPagedData(pagedForm);
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

}
