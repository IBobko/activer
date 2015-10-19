package ru.todo100.activer.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountFriendRelationItem;
import ru.todo100.activer.populators.ProfilePopulator;
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

	@Autowired
	private ProfilePopulator profilePopulator;

	@RequestMapping
	public String index(final Model model)
	{

		Set<AccountFriendRelationItem> friends = accountService.getCurrentAccount().getFriends();

		Set<ProfileData> friendsData = new HashSet<>();

		for (AccountFriendRelationItem friend : friends)
		{
			ProfileData friendData = profilePopulator.populate(friend.getFriendAccount());
			friendsData.add(friendData);
		}

		model.addAttribute("friends", friendsData);

		return "friend/index";
	}
}
