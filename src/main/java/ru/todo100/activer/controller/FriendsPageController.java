package ru.todo100.activer.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import ru.todo100.activer.dao.FriendDao;
import ru.todo100.activer.data.FriendData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.dao.AccountDao;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/friend")
public class FriendsPageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private ProfilePopulator profilePopulator;

	@Autowired
	private FriendDao friendDao;

	@RequestMapping
	public String index(final Model model)
	{
		final AccountItem accountItem = accountService.getCurrentAccount();
		final FriendData friends = friendDao.getFriends(accountItem.getId());
		model.addAttribute("friendData",friends);
		return "friend/index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String search(final Model model)
	{
		final List<AccountItem> searchResult = accountService.getAll();
		model.addAttribute("searchResult",searchResult);
		return "friend/index";
	}
}
