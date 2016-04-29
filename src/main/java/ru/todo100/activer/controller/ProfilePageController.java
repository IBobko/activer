package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.WallDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.form.ChangeProfileForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.service.PhotoService;
import ru.todo100.activer.util.InputError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/profile")
public class ProfilePageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private WallDao wallService;
	@Autowired
	private ProfilePopulator profilePopulator;

	@Autowired
	private PhotoService photoService1;

	@Autowired
	private WallPopulator wallPopulator;

	@RequestMapping(method = RequestMethod.GET)
	public String index(final Model model)
	{
		final AccountItem account = accountService.getCurrentAccountForProfile();
		final String photo = photoService1.getPhoto(account.getId());
		final ProfileData profile = profilePopulator.populate(account);
		model.addAttribute("profile", profile);
		model.addAttribute("photo", photo);
		populatePersonOfPage(model, account);
		return "profile/index";
	}

	@RequestMapping(value = "/change")
	public String change(HttpServletRequest request, Model model, @ModelAttribute final ChangeProfileForm changeProfileForm)
					throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AccountItem account = accountService.get(auth.getName());
		model.addAttribute("account", account);

		changeProfileForm.setEmail(account.getEmail());
		changeProfileForm.setFirstName(account.getFirstName());
		changeProfileForm.setLastName(account.getLastName());

		model.addAttribute("changeProfileForm", changeProfileForm);

		if (request.getMethod().equals("POST"))
		{
			InputError ie = new InputError();
			if (!changeProfileForm.getPassword().trim().equals(""))
			{
				if (!changeProfileForm.getPassword().equals(changeProfileForm.getRepeatPassword()))
				{
					ie.addError("Repeat password is not matched");
				}
				else
				{
					account.setPassword(changeProfileForm.getRepeatPassword());
				}
			}

			if (ie.getErrors().size() != 0)
			{
				model.addAttribute("ie", ie);
			}
			else
			{
				accountService.save(account);
				model.addAttribute("success", true);
			}
		}
		return "profile/change";
	}

	@RequestMapping(value = "/id{id:\\d+}", method = RequestMethod.GET)
	public String people(Model model, @PathVariable Integer id)
	{
		final AccountItem account = accountService.get(id);
		ProfileData profile = profilePopulator.populate(account);
		Set<AccountItem> friends = account.getFriends();
		List<ProfileData> friendsData = new ArrayList<>();
		for (AccountItem friend : friends)
		{
			if (friend != null)
			{
				ProfileData friendData = profilePopulator.populate(friend);
				friendsData.add(friendData);
			}
		}
		profile.setFriends(friendsData);

		String photo =  photoService1.getPhoto(profile.getId());

		model.addAttribute("profile", profile);
		model.addAttribute("photo", photo);

		populatePersonOfPage(model,account);
		return "profile/index";
	}

	@RequestMapping(value = "/{id:\\d+}/add", method = RequestMethod.GET)
	@ResponseBody
	public void addToFriend(final HttpServletResponse response, @PathVariable final Integer id) throws IOException
	{
		accountService.addFriend(accountService.getCurrentAccount(), id);
		response.getOutputStream().print("Successful");
	}

	private void populatePersonOfPage(Model model, AccountItem account) {
		model.addAttribute("personOfPage", account);

		final List<MessageData> wall = new ArrayList<>();
		final List<WallItem> posts = wallService.getAllByAccount(account.getId());
		for (final WallItem item: posts) {
			final MessageData data = wallPopulator.populate(item);
			wall.add(data);
		}
		model.addAttribute("wallTemplate", generateWallTemplate());
		model.addAttribute("wall",wall);
	}

	public MessageData generateWallTemplate() {
		final MessageData template = new MessageData();
		template.setText("%text%");
		final MessageAccountData sender = new MessageAccountData();
		sender.setFirstName("%firstName%");
		sender.setLastName("%lastName%");
		sender.setPhoto60x60("%photo60x60%");
		template.setDate("%date%");
		template.setSender(sender);
		return template;
	}

}
