package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.FriendsData;
import ru.todo100.activer.data.FriendsData1;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.FriendsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/friend")
public class FriendsPageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private FriendsService friendsService;

	@RequestMapping(value = {"","/in","/out","/search"})
	public String index(final Model model, HttpServletRequest request)
	{
		String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		if (pattern.contains("/in")) {
			model.addAttribute("listType","in");
		}

		if (pattern.contains("/out")) {
			model.addAttribute("listType","out");
		}

		if (pattern.contains("/search")) {
			model.addAttribute("listType","search");

			final List<AccountItem> searchResult = accountService.getAll();
			model.addAttribute("searchResult",searchResult);
			return "friend/index";

		}

		model.addAttribute("pageType","friends");
		final FriendsData friends = friendsService.getFriendData(request.getSession());
		model.addAttribute("friendData",friends);
		return "friend/index";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id,HttpServletRequest request)
	{
		friendsService.deleteFriend(id);
		friendsService.synchronize(request.getSession());
		return "redirect:/friend";
	}

	@RequestMapping(value = "/add/{id}")
	public String add(@PathVariable Integer id,HttpServletRequest request)
	{
		friendsService.add(id);
		friendsService.synchronize(request.getSession());
		return "redirect:/friend";
	}

	@ResponseBody
	@RequestMapping(value = "/ajax")
	public FriendsData1 ajax(final HttpServletRequest request) {
		return friendsService.getFriendData1(request.getSession());
	}
}
