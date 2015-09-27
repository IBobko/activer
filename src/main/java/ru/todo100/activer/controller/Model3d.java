package ru.todo100.activer.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

import ru.todo100.activer.model.AccountBuyItem;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.model.OrderItem;
import ru.todo100.activer.service.AccountBuyService;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.MarkService;
import ru.todo100.activer.service.Model3dService;
import ru.todo100.activer.service.OrderService;
import ru.todo100.activer.service.ParameterService;
import ru.todo100.activer.util.InputError;
import ru.todo100.activer.util.Robokassa;
import ru.todo100.activer.util.SaveModel3d;

@Controller
@RequestMapping(value = "/model3d")
@SuppressWarnings(value={"unchecked"})
public class Model3d implements ServletContextAware {
	@Autowired
	Model3dService model3dService;
	
	ServletContext servletContext;
	
	@Autowired	
	MarkService markService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	SaveModel3d saveModel3d;
	
	@Autowired
	OrderService orderService;

	@Autowired	
	AccountBuyService accountBuyService;
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	ParameterService parameterService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		List<Model3dItem> list =  model3dService.getAll();
		model.addAttribute("model3ds",list);
		return "model3d/index";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Model3dItem item = model3dService.get(id);
		model.addAttribute("model3d", item);
		return "model3d/show";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Model3dItem item = model3dService.get(id);
		model.addAttribute("model3d", item);
		return "model3d/edit";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editPost(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Model3dItem item = model3dService.get(id);
		model.addAttribute("model3d", item);
		return "redirect:/model3d/edit/" + id;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "model3d/upload";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadPost(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model,@RequestParam(value="image",required=false) Part image,@RequestParam(value="file",required=false) Part file) throws Exception {
		Long id = 0l;
		try {
			saveModel3d.model3dService = this.model3dService;
			saveModel3d.servletContext = this.servletContext;
			id = saveModel3d.save(request,new Part[]{image},file);
		} catch (InputError inputError) {
			List<String> errors = inputError.getErrors();
			model.addAttribute("errors", errors);
			return "model3d/upload";
		}
		return "redirect:/model3d/show/" + id;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")	
	@RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
	public String purchase(@PathVariable Long id, HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		OrderItem orderItem = new OrderItem();
		orderItem.setAccount(accountService.getCurrentAccount());
		
		orderItem.setModel3d(model3dService.get(id));
		orderItem.setDate(new java.sql.Date(0));
		orderItem.setStatus(0l);
		
		orderService.save(orderItem);
		

		Model3dItem modelItem = model3dService.get(id);
		
		Robokassa rb = new Robokassa();
		rb.setOrder(orderItem.getId());
		rb.setSum(modelItem.getPrice());
		
		model.addAttribute("model3d", modelItem);
		model.addAttribute("order", orderItem);
		model.addAttribute("robokassa", rb);
		return "model3d/purchase";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		final Integer countOfPage = 9;
		String s = request.getParameter("s");
		String pageStr = request.getParameter("page");
		Integer page = 0;
		if (pageStr != null) {
			try {
				page = Integer.parseInt(pageStr);
			} catch(NumberFormatException ex) {
				//nothing:)
			}
		}
		
		if (s == null) {
			s = "";
		}
		String priceFromStr = request.getParameter("priceFrom");
		String priceToStr = request.getParameter("priceTo");
		
		Float priceFrom = 0f;
		Float priceTo = 0f;
		
		try {
			priceFrom = Float.parseFloat(priceFromStr);
		} catch(NumberFormatException ex) {
			priceFrom = null;
		}
		
		try {
			priceTo = Float.parseFloat(priceToStr);
		} catch(NumberFormatException ex) {
			priceTo = null;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Model3dItem.class);
		
		criteria.add( Restrictions.like("name", "%" + s + "%" ));
		if (priceFrom != null) {
			criteria.add( Restrictions.ge("price",priceFrom));
		}
		
		if (priceTo!= null) {
			criteria.add( Restrictions.le("price",priceTo));
		}
		criteria.setMaxResults(countOfPage);
		criteria.setFirstResult(countOfPage * page);
		List<Model3dItem> models = criteria.list();
		criteria.setFirstResult(0);
		Integer count = (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
		
		model.addAttribute("model3ds", models);
		model.addAttribute("search_s", s);
		model.addAttribute("search_priceForm", priceFromStr);
		model.addAttribute("search_priceTo", priceToStr);
		model.addAttribute("pages", ((Double)Math.ceil(count.doubleValue()/countOfPage.doubleValue())).intValue());
		
		return "model3d/search";
	}
	
	@PreAuthorize("hasRole('ROLE_BUYER')")	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public String download(@PathVariable Long id,HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		Model3dItem item = this.model3dService.get(id);
		String file = item.getFile();
		String stlPath = servletContext.getRealPath("/resources/stl") + "/" + file;
		
		java.io.File f = new java.io.File(stlPath);
		
		if (f.exists()) {
			AccountItem account = accountService.getCurrentAccount();
			List<AccountBuyItem> buyList = accountBuyService.getByAccount(account);
			boolean wasBuy = false;
			for (AccountBuyItem buy : buyList) {
				if (buy.getModel3d().equals(item)) {
					wasBuy = true;
					break;
				}
			}
			
			
			if (!wasBuy) {
				if (item.getPrice() < 0.0001 && item.getPrice() > -.0001) {
					AccountBuyItem buyItem = new AccountBuyItem();
					buyItem.setAccount(account);
					buyItem.setModel3d(item);
					accountBuyService.save(buyItem);
				} else {
					return "error/modelNotPaid";
				}
			}
			
			//Increment of count of Download
			item.setDownloadCount(item.getDownloadCount() + 1);
			model3dService.save(item);

			response.setHeader("Content-Disposition", "attachment; filename=" + file);
			InputStream is = null;
			try {
				is = new FileInputStream(stlPath);
				byte[] buffer = new byte[1024];
				int n = 0;
				while (-1 != (n = is.read(buffer))) {
					response.getOutputStream().write(buffer, 0, n);
				}
				response.flushBuffer();
				
			} catch (IOException ex) {
				throw new RuntimeException("IOError writing file to output stream");
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		} else {
			return "error/modelFileNotFound";
		}
	}
	
	@RequestMapping(value = "/pictures/{file:.+}", method = RequestMethod.GET)
	public String pictures(@PathVariable String file, HttpServletResponse response, HttpServletRequest request) {
		InputStream is = null;
		try {
			ServletOutputStream out = response.getOutputStream();
			String path = parameterService.getValue("picture_path");
			String fileName = path + file;
			java.io.File f = new java.io.File(fileName);
			if (f.exists()) {
				is = new FileInputStream(fileName);
				byte[] buffer = new byte[1024];
				int n = 0;
				while (-1 != (n = is.read(buffer))) {
					out.write(buffer, 0, n);
				}
				response.flushBuffer();
				is.close();
			}
			
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
		return null;
	}

}
