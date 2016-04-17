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
import ru.todo100.activer.data.PagedData;
import ru.todo100.activer.data.PartnerData;
import ru.todo100.activer.data.PartnerQualifier;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PartnerService;
import ru.todo100.activer.service.ReferService;

import javax.servlet.http.HttpServletRequest;
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
    @Value("${admin.partner.perpage}")
    private Integer COUNT_PER_PAGE;

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
        //final List<AccountItem> partners = getPartnerService().getPartners(accountId);
        //model.addAttribute("partners", partners);

        List<AccountItem> accounts = accountService.getAll();

        model.addAttribute("accounts", accounts);


        return "admin/creator";
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
    public PagedData partner1(final PagedForm pagedForm) {
        return pagedFormToPagedData(pagedForm);
    }

    public PagedData pagedFormToPagedData(final PagedForm pagedForm) {
        final Integer accountId = accountService.getCurrentAccount().getId();
        final PartnerQualifier qualifier = new PartnerQualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        qualifier.setOwnerAccountId(accountId);

        /*@todo add filter and orders*/

        final Long count = getPartnerService().getPartnersCount(qualifier);
        final PagedData<PartnerData> pagedData = new PagedData<>();
        pagedData.setPage(pagedForm.getPage());
        pagedData.setCount((int) Math.ceil(count*1.0 / COUNT_PER_PAGE));
        pagedData.setElements(getPartnerService().getPartners(qualifier));
        return pagedData;
    }

}
