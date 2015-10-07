package ru.todo100.activer.controller;

/**
 * @author Igor Bobko
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.MessageService;

@Controller
public class MessageController
{
	@Autowired
	private MessageService messageService;

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private AccountService accountService;

	@MessageMapping("/message1/{id}")
	@SendTo("/message1/{id}")
	public MessageData message(@DestinationVariable String id, final MessageData messageData)
	{
		MessageItem messageItem = new MessageItem();
		messageItem.setAccountTo(Integer.parseInt(id));
		AccountItem accountItem = accountService.getCurrentAccount();
		messageItem.setAccountFrom(accountItem.getId().intValue());
		messageItem.setText(messageData.getText());
		messageService.save(messageItem);
		return messageData;
	}

	@Autowired
	private ProfilePopulator profilePopulator;

	@RequestMapping("message")
	public String index()
	{
		return "message/index";
	}

	@RequestMapping("message/{id:\\d+}")
	public String profileMessage(Model model, @PathVariable Integer id)
	{
		final AccountItem friend = accountService.get(id.longValue());
		ProfileData friendData = profilePopulator.populate(friend);
		model.addAttribute("friend", friendData);

		model.addAttribute("lastMessages", messageService.getLastDialogs(id));

		return "message/comunicate";
	}
}
