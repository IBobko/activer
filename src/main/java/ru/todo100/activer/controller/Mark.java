package ru.todo100.activer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.service.Model3dService;

@Controller
@RequestMapping(value = "/mark")
@Transactional(readOnly=false)
@SuppressWarnings(value = { "unchecked" })
public class Mark {
	
	@Autowired
	Model3dService model3dService;
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	
	public String index(HttpServletResponse response,HttpServletRequest request,String locale,Model model) throws InterruptedException {
		Session session = sessionFactory.getCurrentSession();

		String s = request.getParameter("s");
		
		Criteria criteria = session.createCriteria(Model3dItem.class);
		criteria.createAlias("marks", "m");
		criteria.add(Restrictions.eq("m.name",s));
		List<Model3dItem> models = (List<Model3dItem>) criteria.list();
		
		model.addAttribute("models", models);
		model.addAttribute("s", s);		
		return "mark/index";
	}
}

