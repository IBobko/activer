package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.SettingDao;
import ru.todo100.activer.form.PrivateForm;
import ru.todo100.activer.model.AccountItem;

import javax.validation.Valid;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/private")
@PreAuthorize("isAuthenticated()")
public class PrivatePageController {

    private SettingDao settingService;
    private AccountDao accountService;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public SettingDao getSettingService() {
        return settingService;
    }

    @Autowired
    public void setSettingService(SettingDao settingService) {
        this.settingService = settingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Model model) {
        model.addAttribute("pageType", "settings");
        final AccountItem accountItem = getAccountService().getCurrentAccount();
        if (!model.containsAttribute("privateForm")) {
            final PrivateForm privateForm = new PrivateForm();
            privateForm.setShowOnline(Boolean.valueOf(getSettingService().getAccountSetting(accountItem.getId(), "showOnline")));
            privateForm.setShowPremium(Boolean.valueOf(getSettingService().getAccountSetting(accountItem.getId(), "ShowPremium")));
            model.addAttribute("privateForm", privateForm);
        }
        return "private/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid final PrivateForm privateForm, final BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            final AccountItem accountItem = getAccountService().getCurrentAccount();
            getSettingService().setAccountSetting(accountItem.getId(), "showOnline", String.valueOf(privateForm.getShowOnline()));
            getSettingService().setAccountSetting(accountItem.getId(), "showPremium", String.valueOf(privateForm.getShowPremium()));
        }
        return "redirect:/private";
    }

}
