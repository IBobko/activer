package ru.todo100.activer.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.WishItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.WishService;


@Controller
@RequestMapping(value = "/wish")
@PreAuthorize("isAuthenticated()")
@SuppressWarnings(value={"unchecked"})
public class Wish {
	final Integer countOnPage = 10;
	
	@Autowired
	public WishService wishService;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {

		String pageParam = request.getParameter("page");
		Double page = 0.;
		if (pageParam != null) {
			try {
				page = Double.parseDouble(pageParam);
			}catch(NumberFormatException n) {
				// nothing :)
			}
		}
		
		Criteria criteria = wishService.getCriteria();
		criteria.setMaxResults(countOnPage);
		criteria.setFirstResult(((Double)(page*countOnPage)).intValue());
		criteria.addOrder(org.hibernate.criterion.Order.desc("date"));
		
		List<WishItem> wishes = criteria.list();
		
		Long count = ((Number) wishService.getCriteria().setProjection(Projections.rowCount()).uniqueResult()).longValue();	
		
		
		
		model.addAttribute("wishes", wishes);
		model.addAttribute("count",((Double)Math.ceil(count.doubleValue()/countOnPage)).intValue());
		return "wish/index";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String indexPost(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		String wishText = request.getParameter("wish");
		WishItem wishItem = new WishItem();
		Date date = new Date(System.currentTimeMillis());

		

		wishItem.setAccount(accountService.getCurrentAccount());
		wishItem.setText(wishText);
		wishItem.setDate(date);
		wishService.save(wishItem);
		return "redirect:/wish/";
	}

}

