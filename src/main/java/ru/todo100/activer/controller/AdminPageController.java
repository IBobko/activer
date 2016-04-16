package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.PagedData;
import ru.todo100.activer.data.PartnerData;
import ru.todo100.activer.data.Qualifier;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PartnerService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Autowired
    private AccountDao accountService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private SimpMessagingTemplate template;

    public AccountDao getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public PartnerService getPartnerService() {
        return partnerService;
    }

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
    public String creator(final Model model) {
        model.addAttribute("pageType", "admin/creator");
        Integer accountId = accountService.getCurrentAccount().getId();
        final List<AccountItem> partners = getPartnerService().getPartners(accountId);
        model.addAttribute("partners", partners);

        List<AccountItem> accounts = accountService.getAll();

        model.addAttribute("accounts",accounts);


        return "admin/creator";
    }

    @RequestMapping("/partner")
    public String partner(final Model model,final PagedForm pagedForm) {
        model.addAttribute("pageType", "admin/partner");
        Integer accountId = accountService.getCurrentAccount().getId();
        final List<AccountItem> partners = getPartnerService().getPartners(accountId);
        model.addAttribute("partners", partners);
        model.addAttribute("pagedData",pagedFormToPagedData(pagedForm));
        model.addAttribute("defaultPartner",pagedFormToPagedData(pagedForm));
        return "admin/partner";
    }

    private final Integer COUNT_PER_PAGE = 2;

    @ResponseBody
    @RequestMapping("/partnerPaged")
    public PagedData partner1(final PagedForm pagedForm) {
        return pagedFormToPagedData(pagedForm);
    }

    public PagedData pagedFormToPagedData(final PagedForm pagedForm) {
        final PagedData<PartnerData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());

        final Qualifier qualifier = new Qualifier();

        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        qualifier.setCount(COUNT_PER_PAGE);

        List<AccountItem> accounts = accountService.getByQualifier(qualifier);
        Long count = accountService.getCountByQualifier(qualifier);

        pagedData.setCount((int)Math.ceil(count / COUNT_PER_PAGE));


        final List<PartnerData> elements = new ArrayList<>();
        for (AccountItem account: accounts) {
            final PartnerData partnerData = new PartnerData();
            partnerData.setName(account.getFirstName() + " " + account.getLastName());
            elements.add(partnerData);
        }

        pagedData.setElements(elements);
        return pagedData;
    }


}
