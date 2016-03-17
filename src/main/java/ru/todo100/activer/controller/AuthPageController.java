package ru.todo100.activer.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.MailService;

@Controller
@RequestMapping(value = "/auth")
public class AuthPageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private MailService mail;
	@Autowired
	private PromoService promoService;
	@Autowired
	private ReferService referService;

	public PromoService getPromoService() {
		return promoService;
	}

	public void setPromoService(PromoService promoService) {
		this.promoService = promoService;
	}

	public ReferService getReferService() {
		return referService;
	}

	public void setReferService(ReferService referService) {
		this.referService = referService;
	}

	@RequestMapping(value = "")
	public String index()
	{
		return "auth/index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest request,Model model)
	{
		String refer = request.getParameter("refer");
		String promo = request.getParameter("promo");

		AccountItem referAccount = getReferService().getUserByRefer(refer);
		if (referAccount != null) {
			model.addAttribute("referAccount",refer);
			return "auth/signup";
		}

		PromoCodeItem promoCode = getPromoService().getPromo(promo);
		if (promoCode != null && promoCode.getUsed() == null) {
			if (promoCode.getUsed() == null) {
				model.addAttribute("promo",promoCode);
				return "auth/signup";
			} else {
				model.addAttribute("promoUsed",promoCode);
			}
		}
		return "auth/deny";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(HttpServletRequest request, Model model)
	{
		try
		{
			AccountItem account = accountService.saveByRequest(request);
			mail.sendCompleteSignUp(account);
			return "auth/done";
		}
		catch (InputError e)
		{
			model.addAttribute("ie", e);
			return "auth/signup";
		}
	}

	@RequestMapping(value = "/loginfail")
	public String loginfail(Model model)
	{
		InputError ie = new InputError();
		ie.addError("Login or password incorrect");
		model.addAttribute("ie", ie);
		return "auth/index";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot()
	{
		return "auth/forgot";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotPost(HttpServletRequest request, Model model)
	{
		String email = request.getParameter("email");
		AccountItem account = accountService.getByEmail(email);
		if (account != null)
		{
			String password = RandomStringUtils.random(8, true, true);
			account.setPassword(password);
			accountService.save(account);
			mail.sendForgotPassword(account);
			model.addAttribute("account", account);
		}
		else
		{
			InputError ie = new InputError();
			ie.addError("User not found");
			model.addAttribute("ie", ie);
		}
		return "auth/forgot";
	}

	@RequestMapping(value = "/denied")
	public String denied()
	{
		return "auth/denied";
	}

	@RequestMapping(value = "/agreement/commission")
	public String commision()
	{
		return "auth/agree/commission";
	}

	@RequestMapping(value = "/agreement/buysell")
	public String buysell()
	{
		return "auth/agree/buysell";
	}
}
