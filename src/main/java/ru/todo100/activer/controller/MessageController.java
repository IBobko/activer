package ru.todo100.activer.controller;

/**
 * @author Igor Bobko
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.MessageDao;

@Controller
public class MessageController
{

	@Autowired
	private MessageDao messageService;

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private AccountDao accountService;

	@Autowired
	private ProfilePopulator profilePopulator;

	@MessageMapping("/message1/1")
	@SendTo("/message2/{id}")
	public MessageData message(final MessageData messageData/*, Principal principal*/)
	{
		System.out.println(messageData);
//		MessageItem messageItem = new MessageItem();
//		messageItem.setAccountTo(messageData.getAccountDataTo().getId());
//		AccountItem accountItem = accountService.get(principal.getName());
//		messageItem.setAccountFrom(accountItem.getId().intValue());
//		messageItem.setText(messageData.getText());
//		messageItem.setAddedDate(new GregorianCalendar());
//		messageService.save(messageItem);
//		AccountItem accountTo = accountService.get(messageData.getAccountTo());
//		MessageAccountData accountDataTo = new MessageAccountData();
//		accountDataTo.setFirstName(accountTo.getFirstName());
//		accountDataTo.setLastName(accountTo.getLastName());
//		messageData.setAccountDataTo(accountDataTo);
//
//		MessageAccountData accountDataFrom = new MessageAccountData();
//		accountDataFrom.setFirstName(accountItem.getFirstName());
//		accountDataFrom.setLastName(accountItem.getLastName());
//		messageData.setSender(accountDataFrom);
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
//		messageData.setDate(format.format(new GregorianCalendar().getTime()));
//
//		template.convertAndSend("/message2/" + messageData.getAccountTo(), messageData);
//
//		return messageData;
		return null;
	}

	@RequestMapping("/message")
	public String index(final Model model)
	{
		System.out.println("Hey! Hello world!!!");
		final Set<AccountItem> friends = accountService.getCurrentAccount().getFriends();
		final Set<ProfileData> friendsData = new HashSet<>();
		for (AccountItem friend:friends) {
			friendsData.add(profilePopulator.populate(friend));
		}
		model.addAttribute("friends", friendsData);
		return "message/index";
	}

	@RequestMapping("/message/{id:\\d+}")
	public String profileMessage(final Model model, @PathVariable Integer id)
	{
		System.out.println("hello world!!!");

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
		model.addAttribute("templatePost",generateTemplateMessageData());
		return "message/communicate";
	}

	public MessageData generateTemplateMessageData() {
		final MessageData template = new MessageData();
		template.setText("%text%");
		final MessageAccountData sender = new MessageAccountData();
		sender.setFirstName("%firstName%");
		sender.setLastName("%lastName%");
		template.setDate("%date%");
		template.setSender(sender);
		return template;
	}
}
