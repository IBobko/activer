package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.form.ChangePasswordForm;
import ru.todo100.activer.model.AccountItem;

import javax.validation.Valid;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/safety")
@PreAuthorize("isAuthenticated()")
public class SafetyPageController {
    @Autowired
    private AccountDao accountService;

    public AccountDao getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("pageType", "settings/safety");

        if (!model.containsAttribute("changePasswordForm")){
            final ChangePasswordForm changePasswordForm = new ChangePasswordForm();
            model.addAttribute("changePasswordForm",changePasswordForm);
        }

        return "safety/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@Valid final ChangePasswordForm changePasswordForm, final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        final AccountItem account = getAccountService().getCurrentAccount();
        if (!changePasswordForm.getOldPassword().equals(account.getPassword())) {
            bindingResult.rejectValue("newPassword", "error.newPassword", "Предыдущий пароль не верен");
        }
        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getRepeatedPassword())) {
            bindingResult.rejectValue("repeatedPassword", "error.newPassword", "Пароли не совпадают");
        }

        if (!bindingResult.hasErrors()) {
            account.setPassword(changePasswordForm.getRepeatedPassword());
            accountService.save(account);
            redirectAttributes.addFlashAttribute("passwordSaved", true);
        }

        redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "changePasswordForm", bindingResult);
        redirectAttributes.addFlashAttribute("changePasswordForm", changePasswordForm);

        return "redirect:/safety";

    }

}
