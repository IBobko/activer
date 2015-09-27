package ru.todo100.activer.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.Model3dService;

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Admin {
	@Autowired
	Model3dService model3dService;
	@Autowired
	AccountService accountService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {

		return "admin/index";
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String accounts(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		List<AccountItem> accounts = accountService.getAll();
		model.addAttribute("accounts", accounts);
		return "admin/accounts";
	}
	
	@RequestMapping(value = "/model3ds", method = RequestMethod.GET)
	public String model3ds(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		List<Model3dItem> models = model3dService.getAll();
		model.addAttribute("model3ds",models);
		return "admin/model3ds";
	}
}

