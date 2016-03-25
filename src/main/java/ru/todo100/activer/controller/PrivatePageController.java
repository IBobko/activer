package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.SettingDao;
import ru.todo100.activer.form.PrivateForm;
import ru.todo100.activer.model.AccountItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/private")
@PreAuthorize("isAuthenticated()")
public class PrivatePageController {

    @Autowired
    private SettingDao settingService;


    @Autowired
    private AccountDao accountService;

    public SettingDao getSettingService() {
        return settingService;
    }

    public void setSettingService(SettingDao settingService) {
        this.settingService = settingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(final Model model) {
        AccountItem accountItem = accountService.getCurrentAccount();

        getSettingService().isAccountSetting(accountItem.getId(),"showOnline");

        model.addAttribute("pageType", "settings");

        model.addAttribute("privateForm", new PrivateForm());
        return "private/index";
    }
}
