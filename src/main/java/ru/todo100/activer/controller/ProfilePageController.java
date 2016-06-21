package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.FriendDao;
import ru.todo100.activer.dao.PhotosDao;
import ru.todo100.activer.dao.WallDao;
import ru.todo100.activer.data.*;
import ru.todo100.activer.form.ChangeProfileForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.service.FriendsService;
import ru.todo100.activer.service.PhotoService;
import ru.todo100.activer.util.InputError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/profile")
public class ProfilePageController
{
	@Autowired
	private AccountDao accountService;

	@Autowired
	private PhotosDao photosDao;

	@Autowired
	private WallDao wallService;

	@Autowired
	private ProfilePopulator profilePopulator;

	@Autowired
	private PhotoService photoService1;

	@Autowired
	private WallPopulator wallPopulator;

	@Autowired
	private FriendsService friendsService;

	@Autowired
	private FriendDao friendDao;

	@RequestMapping(method = RequestMethod.GET)
	public String index(final HttpServletRequest request)
	{
		final ProfileData profile = accountService.getCurrentProfileData(request.getSession());
		return "redirect:/profile/id" + profile.getId();
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
	public String people(Model model, @PathVariable Integer id,HttpServletRequest request)
	{
		ProfileData profile = accountService.getCurrentProfileData(request.getSession());
		ProfileData currentProfile = accountService.getCurrentProfileData(request.getSession());

		if (!profile.getId().equals(id)) {
			final AccountItem account = accountService.get(id);
			profile = profilePopulator.populate(account);
			FriendsData friends = friendDao.getFriends(profile.getId());


			for (FriendData friendData: friends.getFriends()) {
				if (friendData.getId().equals(currentProfile.getId())){
					profile.setFriend(true);
				}
			}

			for (FriendData friendData: friends.getOutRequest()) {
				if (friendData.getId().equals(currentProfile.getId())){
					profile.setFriend(true);
				}
			}
			model.addAttribute("friends", friendDao.getFriends(profile.getId()));
		} else {
			model.addAttribute("friends", friendsService.getFriendData(request.getSession()));
		}

		final List<PhotoItem> photos = photosDao.getByAccount(profile.getId());

		final PhotoAvatarSizeData avatarPhotos = photoService1.getSizedPhoto(profile.getId());

		model.addAttribute("profile", profile);

		model.addAttribute("photos", photos);

		model.addAttribute("showingPhoto", avatarPhotos.getPhotoShowing());
		model.addAttribute("photo", avatarPhotos.getPhotoAvatar());
		populatePersonOfPage(model, profile);
		return "profile/index";
	}

	@RequestMapping(value = "/{id:\\d+}/add", method = RequestMethod.GET)
	@ResponseBody
	public void addToFriend(final HttpServletResponse response, @PathVariable final Integer id) throws IOException
	{
		accountService.addFriend(accountService.getCurrentAccount(), id);
		response.getOutputStream().print("Successful");
	}

	private void populatePersonOfPage(Model model, ProfileData account) {
		final List<MessageData> wall = new ArrayList<>();
		final List<WallItem> posts = wallService.getAllByAccount(account.getId());
		for (final WallItem item: posts) {
			final MessageData data = wallPopulator.populate(item);
			wall.add(data);
		}
		model.addAttribute("wall",wall);
	}

	@ExceptionHandler(Exception.class)
	public void handleNullPointerException(NullPointerException ex) {
		System.out.println("hello world");
	}
}
