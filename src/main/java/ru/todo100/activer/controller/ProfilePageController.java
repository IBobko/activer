package ru.todo100.activer.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.data.ICanData;
import ru.todo100.activer.data.IWantData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.facade.MarkFacade;
import ru.todo100.activer.form.ChangeProfileForm;
import ru.todo100.activer.form.ICanForm;
import ru.todo100.activer.form.IWantForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.model.MarkItem;
import ru.todo100.activer.model.MarkRelationItem;
import ru.todo100.activer.populators.ICanPopulator;
import ru.todo100.activer.populators.IWantPopulator;
import ru.todo100.activer.populators.ProfilePopulator;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.ICanService;
import ru.todo100.activer.service.IWantService;
import ru.todo100.activer.service.MarkRelationService;
import ru.todo100.activer.service.MarkService;
import ru.todo100.activer.strategy.PhotoStrategy;
import ru.todo100.activer.util.InputError;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/profile")
public class ProfilePageController
{
	@Autowired
	private AccountService accountService;

	@Autowired
	private ICanService iCanService;

	@Autowired
	private IWantService iWantService;

	@Autowired
	private ICanPopulator iCanPopulator;

	@Autowired
	private MarkFacade markFacade;

	@Autowired
	private IWantPopulator iWantPopulator;

	@Autowired
	private MarkService markService;

	@Autowired
	private MarkRelationService markRelationService;

	@Autowired
	private PhotoStrategy photoStrategy;

	@Autowired
	private ProfilePopulator profilePopulator;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model)
	{
		final AccountItem account = accountService.getCurrentAccount();

		ProfileData profile = profilePopulator.populate(account);

		model.addAttribute("profile", profile);
		model.addAttribute("cans", iCanService.getAll());
		model.addAttribute("wants", iWantService.getAll());
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

		System.out.println(changeProfileForm.getFacePhoto());

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

				if (!changeProfileForm.getFacePhoto().isEmpty())
				{
					System.out.println("Start");
					System.out.println(photoStrategy.saveFile(changeProfileForm.getFacePhoto()));
				}

				model.addAttribute("success", true);
			}
		}
		return "profile/change";
	}

	@RequestMapping(value = "/add_i_can", method = RequestMethod.GET)
	public String addICan(Model model)
	{
		model.addAttribute("iCanForm", new ICanForm());
		return "profile/i_can_form";
	}

	@RequestMapping(value = "/add_i_can", method = RequestMethod.POST)
	public String doAddICan(@ModelAttribute ICanForm iCanForm)
	{
		final AccountItem accountItem = accountService.getCurrentAccount();
		ICanItem canItem = iCanService.add(accountItem, iCanForm);

		String[] marks = StringUtils.tokenizeToStringArray(iCanForm.getMarks(), ",", true, true);

		for (String mark : marks)
		{
			MarkItem item = markService.findMark(mark.trim());
			if (item == null)
			{
				item = markService.addMark(mark.trim());
			}

			if (canItem.getId() != null)
			{
				markRelationService.clear(canItem.getId(), true);
			}

			MarkRelationItem rel = markRelationService.findRelation(canItem.getId(), item.getId(), true);
			if (rel == null)
			{
				markRelationService.addRelation(canItem.getId(), item.getId(), true);
				item.setCount(item.getCount() + 1);
				markService.save(item);
			}
		}

		return "redirect:/profile";
	}

	@RequestMapping(value = "/add_i_want", method = RequestMethod.GET)
	public String addIWant(Model model)
	{
		model.addAttribute("iWantForm", new IWantForm());
		return "profile/i_want_form";
	}

	@RequestMapping(value = "/add_i_want", method = RequestMethod.POST)
	public String doAddIWant(@ModelAttribute IWantForm iWantForm)
	{

		final AccountItem accountItem = accountService.getCurrentAccount();
		iWantService.add(accountItem, iWantForm);
		return "redirect:/profile";
	}

	@RequestMapping(value = "/edit_i_can/{id}", method = RequestMethod.GET)
	public String editICan(Model model, @PathVariable("id") Integer id)
	{
		final ICanItem canModel = iCanService.get(id);
		final ICanData data = iCanPopulator.populate(canModel);
		final ICanForm form = new ICanForm();
		form.setTitle(data.getTitle());
		form.setDescription(data.getDescription());
		form.setId(data.getId());
		form.setMarks(markFacade.MarksToStrings(data.getMarkData()));
		model.addAttribute("iCanForm", form);
		return "profile/i_can_form";
	}

	@RequestMapping(value = "/edit_i_can/{id}", method = RequestMethod.POST)
	public String doEditICan(@ModelAttribute ICanForm iCanForm, @PathVariable("id") String id)
	{
		final AccountItem accountItem = accountService.getCurrentAccount();
		ICanItem canItem = iCanService.add(accountItem, iCanForm);

		String[] marks = StringUtils.tokenizeToStringArray(iCanForm.getMarks(), ",", true, true);

		for (String mark : marks)
		{
			MarkItem item = markService.findMark(mark.trim());
			if (item == null)
			{
				item = markService.addMark(mark.trim());
			}

			if (canItem.getId() != null)
			{
				markRelationService.clear(canItem.getId(), true);
			}

			MarkRelationItem rel = markRelationService.findRelation(canItem.getId(), item.getId(), true);
			if (rel == null)
			{
				markRelationService.addRelation(canItem.getId(), item.getId(), true);
				item.setCount(item.getCount() + 1);
				markService.save(item);
			}
		}
		return "redirect:/profile";
	}

	@RequestMapping(value = "/edit_i_want/{id}", method = RequestMethod.GET)
	public String editIWant(Model model, @PathVariable("id") Integer id)
	{
		final IWantItem iWantModel = iWantService.get(id);
		final IWantData data = iWantPopulator.populate(iWantModel);
		final IWantForm form = new IWantForm();
		form.setTitle(data.getTitle());
		form.setDescription(data.getDescription());
		form.setId(data.getId());
		form.setMarks(markFacade.MarksToStrings(data.getMarkData()));

		model.addAttribute("iWantForm", form);
		return "profile/i_want_form";
	}

	@RequestMapping(value = "/edit_i_want/{id}", method = RequestMethod.POST)
	public String doEditIWant(@ModelAttribute IWantForm iWantForm, @PathVariable("id") String id)
	{
		final AccountItem accountItem = accountService.getCurrentAccount();
		IWantItem wantItem = iWantService.add(accountItem, iWantForm);

		String[] marks = StringUtils.tokenizeToStringArray(iWantForm.getMarks(), ",", true, true);

		for (String mark : marks)
		{
			MarkItem item = markService.findMark(mark.trim());
			if (item == null)
			{
				item = markService.addMark(mark.trim());
			}

			if (wantItem.getId() != null)
			{
				markRelationService.clear(wantItem.getId(), false);
			}

			MarkRelationItem rel = markRelationService.findRelation(wantItem.getId(), item.getId(), false);
			if (rel == null)
			{
				markRelationService.addRelation(wantItem.getId(), item.getId(), false);
				item.setCount(item.getCount() + 1);
				markService.save(item);
			}
		}
		return "redirect:/profile";
	}

	@RequestMapping(value = "/id{id:\\d+}", method = RequestMethod.GET)
	public String people(Model model, @PathVariable Integer id)
	{
		final AccountItem account = accountService.get(id.longValue());
		model.addAttribute("account", account);
		model.addAttribute("cans", iCanService.getAll());
		model.addAttribute("wants", iWantService.getAll());
		return "profile/index";
	}
}
