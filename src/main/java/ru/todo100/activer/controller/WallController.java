package ru.todo100.activer.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.GregorianCalendar;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ReceiveWallData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.WallDao;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/wall")
public class WallController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private WallPopulator wallPopulator;

	@Autowired
	private WallDao wallService;


	@RequestMapping("/publish")
	@ResponseBody
	public void publish(@ModelAttribute ReceiveWallData receiveWallData, BindingResult bindingResult, HttpServletResponse response) throws IOException {
		final AccountItem account = accountService.get(receiveWallData.getId());
		final AccountItem currentAccount = accountService.getCurrentAccount();
		WallItem post = new WallItem();
		post.setAccount(account);
		post.setText(receiveWallData.getText());
		post.setAddedDate(new GregorianCalendar());
		post.setSender(currentAccount.getId());
		wallService.save(post);
		final JSONObject jsonObject = new JSONObject(wallPopulator.populate(post));
		response.getWriter().println(jsonObject.toString());
	}
}



