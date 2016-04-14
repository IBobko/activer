package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PartnerService;

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
        return "admin/creator";
    }

    @RequestMapping("/partner")
    public String partner(final Model model) {
        model.addAttribute("pageType", "admin/partner");
        Integer accountId = accountService.getCurrentAccount().getId();
        final List<AccountItem> partners = getPartnerService().getPartners(accountId);
        model.addAttribute("partners", partners);
        return "admin/partner";
    }
}
