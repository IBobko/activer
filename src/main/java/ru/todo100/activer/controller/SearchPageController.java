package ru.todo100.activer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.todo100.activer.data.MarkSearchData;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.model.MarkItem;
import ru.todo100.activer.model.MarkRelationItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.ICanService;
import ru.todo100.activer.service.IWantService;
import ru.todo100.activer.service.MarkRelationService;
import ru.todo100.activer.service.MarkService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */

@Controller
@RequestMapping(value = "/search")
public class SearchPageController
{
	@Autowired
	private MarkService markService;

	@Autowired
	private MarkRelationService markRelationService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ICanService iCanService;

	@Autowired
	private IWantService iWantService;

	@RequestMapping(method = RequestMethod.GET)
	public String canSearch(Model model, @RequestParam("s") String s)
	{
		final MarkItem item = markService.findMark(s);

		if (item != null)
		{

			final List<MarkRelationItem> items = markRelationService.findByMarkId(item.getId());

			final List<MarkSearchData> result = new ArrayList<>();

			for (MarkRelationItem i : items)
			{
				final MarkSearchData markSearchData = new MarkSearchData();
				if (i.getCW())
				{
					ICanItem i1 = iCanService.get(i.getRelationId());
					markSearchData.setCW(i.getCW());
					markSearchData.setAccount(i1.getAccount());
					markSearchData.setMarkItem(item);
				}
				else
				{
					IWantItem i2 = iWantService.get(i.getRelationId());
					markSearchData.setCW(i.getCW());
					markSearchData.setAccount(i2.getAccount());
					markSearchData.setMarkItem(item);
				}
				result.add(markSearchData);
			}
			model.addAttribute("result", result);
		}
		return "search/index";
	}
}
