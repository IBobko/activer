package ru.todo100.activer.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import ru.todo100.activer.model.BlogItem;
import ru.todo100.activer.service.BlogService;


@Controller
@RequestMapping(value = "/blog")
public class Blog {
	@Autowired
	BlogService blogService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Criteria criteria = blogService.getCriteria();
		List<?> blogs = criteria.list();
		model.addAttribute("blogs",blogs);
		return "blog/index";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {


		
		return "blog/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		
		BlogItem blog = new BlogItem();
		blog.setText(text);
		blog.setTitle(title);
		blog.setAccount(0l);
		blogService.save(blog);
		return "redirect:/blog";
	}
}
