package ru.todo100.activer.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.AccountBuyItem;
import ru.todo100.activer.model.OrderItem;
import ru.todo100.activer.service.AccountBuyService;
import ru.todo100.activer.service.Model3dService;
import ru.todo100.activer.service.OrderService;
import ru.todo100.activer.util.Robokassa;


@Controller
@RequestMapping(value = "/payment")
public class Payment {
	@Autowired
	OrderService orderService;

	@Autowired
	Model3dService model3dService;
	
	@Autowired
	AccountBuyService accountBuyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		return null;
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		String password = "tGTQosyZ1988";   // merchant pass2 here
		String outSum = request.getParameter("OutSum");
		String invId = request.getParameter("InvId");
		String crc = request.getParameter("SignatureValue");
				
		Robokassa rb = new Robokassa();
		
		String myCrc = rb.md5(outSum + ":" + invId + ":" + password).toUpperCase();
		try {		
			if (myCrc.equals(crc)) {
				Long id = Long.parseLong(invId);
				OrderItem order = orderService.get(id);
				order.setStatus(1l);
				orderService.save(order);
				AccountBuyItem accountBuyItem = new AccountBuyItem();
				accountBuyItem.setAccount(order.getAccount());
				accountBuyItem.setModel3d(order.getModel3d());
				accountBuyService.save(accountBuyItem);
				response.getWriter().println("Thank you robokassa for payment!");
			} else {
				response.getWriter().println("bad sign");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String fail(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {
		return "payment/fail";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws IOException {
		String invId = request.getParameter("InvId");		
		Long id = Long.parseLong(invId);
		OrderItem order = orderService.get(id);
		model.addAttribute("model3d", order.getModel3d());
		return "payment/success";
	}
}


