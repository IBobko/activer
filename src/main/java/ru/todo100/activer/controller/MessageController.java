package ru.todo100.activer.controller;

/**
 * @author Igor Bobko
 */

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.todo100.activer.data.MessageAccountData;
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

	@Autowired
	private ProfilePopulator profilePopulator;

	@MessageMapping("/message1/{id}")
	@SendTo("/message2/{id}")
	public MessageData message(final MessageData messageData, Principal principal)
	{
		MessageItem messageItem = new MessageItem();
		messageItem.setAccountTo(messageData.getAccountTo());
		AccountItem accountItem = accountService.get(principal.getName());
		messageItem.setAccountFrom(accountItem.getId().intValue());
		messageItem.setText(messageData.getText());
		messageItem.setAddedDate(new GregorianCalendar());
		messageService.save(messageItem);
		AccountItem accountTo = accountService.get(messageData.getAccountTo());
		MessageAccountData accountDataTo = new MessageAccountData();
		accountDataTo.setFirstName(accountTo.getFirstName());
		accountDataTo.setLastName(accountTo.getLastName());
		messageData.setAccountDataTo(accountDataTo);

		MessageAccountData accountDataFrom = new MessageAccountData();
		accountDataFrom.setFirstName(accountItem.getFirstName());
		accountDataFrom.setLastName(accountItem.getLastName());
		messageData.setSender(accountDataFrom);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		messageData.setDate(format.format(new GregorianCalendar().getTime()));

		template.convertAndSend("/message2/" + messageData.getAccountTo(), messageData);

		return messageData;
	}

	@RequestMapping("/message")
	public String index(final Model model)
	{
		final List<AccountItem> friends = accountService.getFriends(accountService.getCurrentAccount().getId().intValue());
		model.addAttribute("friends", friends);
		return "message/index";
	}

	@RequestMapping("/message/{id:\\d+}")
	public String profileMessage(final Model model, @PathVariable Integer id)
	{
		/** Добавить кэширование **/
		final List<MessageData> messageData = new ArrayList<>();
		List<MessageItem> messageItems = messageService.getLastDialogs(id);
		for (MessageItem item : messageItems)
		{
			final MessageData data = new MessageData();
			final AccountItem from = accountService.get(item.getAccountFrom());
			final MessageAccountData sender = new MessageAccountData();
			sender.setFirstName(from.getFirstName());
			sender.setLastName(from.getLastName());
			data.setSender(sender);
			data.setText(item.getText());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			data.setDate(format.format(item.getAddedDate().getTime()));
			messageData.add(data);
		}

		final AccountItem friend = accountService.get(id);
		ProfileData friendData = profilePopulator.populate(friend);
		model.addAttribute("friend", friendData);
		model.addAttribute("myProfile", accountService.getCurrentAccount());
		model.addAttribute("lastMessages", messageData);
		return "message/communicate";
	}
}
