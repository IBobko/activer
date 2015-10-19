package ru.todo100.activer.controller;

import java.security.Principal;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.WallService;

/**
 * @author Igor Bobko
 */
@Controller
public class WallWebSocketController
{
	@Autowired
	private AccountService accountService;

	@Autowired
	private WallPopulator wallPopulator;

	@Autowired
	private WallService wallService;

	@MessageMapping("/wall/{id}")
	@SendTo("/wall1/{id}")
	public MessageData listenWall(@DestinationVariable final Integer id, final MessageData messageData,Principal principal)
	{
		if (StringUtils.isEmpty(messageData.getText())) {
			return new MessageData();
		}

		final AccountItem account = accountService.get(id);
		final AccountItem currentAccount = accountService.get(principal.getName());
		WallItem post = new WallItem();
		post.setAccountId(account.getId());
		post.setText(messageData.getText());
		post.setAddedDate(new GregorianCalendar());
		post.setSender(currentAccount.getId());

		wallService.save(post);
		return wallPopulator.populate(post);
	}

}



