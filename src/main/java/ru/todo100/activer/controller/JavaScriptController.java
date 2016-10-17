package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/js")
public class JavaScriptController
{
	private AccountDao accountService;

	public AccountDao getAccountService() {
		return accountService;
	}

	@Autowired
	public void setAccountService(AccountDao accountService) {
		this.accountService = accountService;
	}

	@ResponseBody
	@RequestMapping("/data.json")
	public Map data(HttpSession session) {
		final Map<String,Object> json = new HashMap<>();
		json.put("profile", getAccountService().getCurrentProfileData(session));
		return json;
	}
}
