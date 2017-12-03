package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.SettingDao;

import javax.servlet.http.HttpSession;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/themes")
public class ThemesPageController {
    private SettingDao settingService;
    private AccountDao accountService;

    private SettingDao getSettingService() {
        return settingService;
    }

    @Autowired
    public void setSettingService(SettingDao settingService) {
        this.settingService = settingService;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("")
    public String index() {
        return "themes/index";
    }

    @RequestMapping("/update/{theme}")
    public String update(@PathVariable final String theme, final HttpSession session, final Model model) {
        model.addAttribute("pageType", "settings/theme");
        final Integer accountId = getAccountService().getCurrentProfileData(session).getId();
        getSettingService().setAccountSetting(accountId, "theme", theme);
        getAccountService().addSynchronizer(accountId, "theme", theme);
        return "redirect:/themes";
    }

}
