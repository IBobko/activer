package ru.todo100.activer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.*;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.todo100.activer.model.CategoryItem;
import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.service.CategoryService;

@Controller
@SuppressWarnings(value = { "unchecked" })
@RequestMapping(value = "/category")
public class Category  {
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	public CategoryService categoryService;
	
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public String category(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		final Double countOnPage = 9.;
		String pageParam = request.getParameter("page");
		Double page = 0.;
		if (pageParam != null) {
			try {
				page = Double.parseDouble(pageParam);
			}catch(NumberFormatException n) {
				// nothing :)
			}
		}
		Session session = sessionFactory.getCurrentSession();
		Long count = (Long)session.createQuery("select count(*) from Model3dItem where category = :category").setParameter("category", id).iterate().next();
		List<Model3dItem> models = session.createQuery("from Model3dItem c where category = :category").
				setParameter("category", id).
				setFirstResult(((Double)(page*countOnPage)).intValue()).
				setMaxResults(countOnPage.intValue()).list();
		model.addAttribute("model3ds", models);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", id);
		model.addAttribute("count", ((Double)Math.ceil(count.doubleValue()/countOnPage)).intValue());
		return "category/index";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit-list", method = RequestMethod.GET)	
	public String editList(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CategoryItem.class);
		criteria.add(Restrictions.or(Restrictions.isNull("parent"), Restrictions.eq("parent", 0l)));
		List<CategoryItem> categories = criteria.list();
		model.addAttribute("categories",categories);
		return "category/edit-list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/save", method = RequestMethod.POST)	
	public String save(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		String data = request.getParameter("data");
		JSONArray json = new JSONArray(data);
		childrenCategory(json,null);
		return null;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/remove/{id:\\d+}", method = RequestMethod.GET)	
	public String remove(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(CategoryItem.class, id));
		return "redirect:/category/edit-list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id:\\d+}", method = RequestMethod.GET)	
	public String edit(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		Session session = sessionFactory.getCurrentSession();
		CategoryItem category = null;
		if (id.equals(0l)) {
			category = new CategoryItem();
			category.setParent(null);
		} else {
			category = (CategoryItem) session.get(CategoryItem.class, id);
		}
		model.addAttribute("category",category);
		return "category/edit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id:\\d+}", method = RequestMethod.POST)	
	public String editPost(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		String name = request.getParameter("name");
		Session session = sessionFactory.getCurrentSession();
		CategoryItem category = null;
		if (id.equals(0l)) {
			category = new CategoryItem();
			category.setParent(null);
		} else {
			category = (CategoryItem) session.get(CategoryItem.class, id);
		}
		category.setName(name);
		session.saveOrUpdate(category);
		model.addAttribute("category",session.get(CategoryItem.class, id));
		return "redirect:/category/edit-list";
	}
	
	@RequestMapping(value = "/ajax/{action}/{param}", method = RequestMethod.GET)
	public String ajax(@PathVariable String action,@PathVariable String param, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		if (action.equals("get-categories")) {
			response.setContentType("application/json");
			Long parent = 0L;
			try {
				parent = Long.parseLong(param);
			} catch (NumberFormatException e) {
				// nothing:-)
			}
			
			List<CategoryItem> categories = categoryService.getCategories(parent);
			JSONArray jsonArray = new JSONArray();			
			for (CategoryItem ci: categories){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", ci.getName());
				jsonObject.put("id", ci.getId());
				jsonArray.put(jsonObject);				
			}
			response.getWriter().println(jsonArray.toString());
		}
		return null;
	}
	
	public void childrenCategory(JSONArray children,Long parent){
		for (int i = 0; i < children.length(); i++) {
			JSONObject obj = children.getJSONObject(i);
			Long id = obj.getLong("id");
			CategoryItem category = categoryService.get(id);
			if (parent == null || parent.equals(0)) {
				category.setParent(null);
			} else {
				category.setParent(parent);
			}
			categoryService.save(category);
			category = null;
			if (obj.has("children")) {
				JSONArray ch = obj.getJSONArray("children");
				if (ch.length() > 0) {
					childrenCategory(ch,obj.getLong("id"));
				}
			}
		}
	}
}

