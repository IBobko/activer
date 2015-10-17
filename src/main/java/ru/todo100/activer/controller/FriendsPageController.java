package ru.todo100.activer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.todo100.activer.model.AccountFriendRelationItem;
import ru.todo100.activer.service.AccountFriendRelationService;
import ru.todo100.activer.service.AccountService;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/friend")
public class FriendsPageController
{
	@Autowired
	private AccountFriendRelationService accountFriendRelationService;

	@Autowired
	private AccountService accountService;

	@RequestMapping
	public String index(final Model model)
	{

		List<AccountFriendRelationItem> friends = accountFriendRelationService.getFriends(accountService.getCurrentAccount());

		model.addAttribute("friends",friends);





		return "friend/index";
	}
}
