package ru.todo100.activer.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.form.AccountEmailForm;
import ru.todo100.activer.form.RegisterForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/auth")
public class AuthPageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private MailService mail;

	private PromoService promoService;

	private ReferService referService;

	public PromoService getPromoService() {
		return promoService;
	}

	@Autowired
	public void setPromoService(PromoService promoService) {
		this.promoService = promoService;
	}

	public ReferService getReferService() {
		return referService;
	}

	@Autowired
	public void setReferService(ReferService referService) {
		this.referService = referService;
	}

	@RequestMapping
	public String index(final Model model)
	{
		model.addAttribute("pageType", "auth");
		return "auth/index";
	}

	/* @todo Возможно стоит внедрить форму */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest request,Model model)
	{
		model.addAttribute("pageType", "register");
		final RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm",registerForm);
		final String refer = request.getParameter("referCode");
		final String promo = request.getParameter("promoCode");

		final AccountItem referAccount = getReferService().getUserByRefer(refer);
		if (referAccount != null) {
			registerForm.setRefer(refer);
			model.addAttribute("referAccount",referAccount);
			return "auth/signup";
		}

		final PromoCodeItem promoCode = getPromoService().getPromo(promo);
		if (promoCode != null && promoCode.getUsed() == null) {
			registerForm.setPromo(promo);
			if (promoCode.getUsed() == null) {
				return "auth/signup";
			}
		}
		return "auth/signup";
	}

	/* @todo Внедрить binding result */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute RegisterForm registerForm, Model model)
	{
		model.addAttribute("pageType", "register");
		try
		{
			AccountItem account = accountService.saveForm(registerForm);
			mail.sendCompleteSignUp(account);
			return "auth/done";
		}
		catch (InputError e)
		{
			model.addAttribute("ie", e);
			return "auth/signup";
		}
	}

	/* @todo Внедрить redirect */
	@RequestMapping(value = "/loginfail")
	public String loginfail(Model model)
	{
		model.addAttribute("pageType", "register");
		InputError ie = new InputError();
		ie.addError("Login or password incorrect");
		model.addAttribute("ie", ie);
		return "auth/index";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(final Model model)
	{
		model.addAttribute("accountEmailForm", new AccountEmailForm());
		model.addAttribute("pageType", "forgot");
		return "auth/forgot";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotPost(final Model model, @Valid final AccountEmailForm accountEmailForm, final BindingResult bindingResult)
	{
		model.addAttribute("pageType", "forgot");
		if (!bindingResult.hasErrors()) {
			final AccountItem account = accountEmailForm.getAccount();
			String password = RandomStringUtils.random(8, true, true);
			account.setPassword(password);
			accountService.save(account);
			mail.sendForgotPassword(account);
			model.addAttribute("account", account);
		}
		return "auth/forgot";
	}

	@RequestMapping(value = "/denied")
	public String denied(final Model model)
	{
		model.addAttribute("pageType", "denied");
		return "auth/denied";
	}
}
