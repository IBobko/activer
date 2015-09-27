package ru.todo100.activer.controller;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.model.OrderItem;
import ru.todo100.activer.service.OrderService;

@Controller
@RequestMapping(value = "/order")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Order {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws InterruptedException {
		List<OrderItem> orders = orderService.getAll();
		model.addAttribute("orders",orders);
		return "order/index";
	}
}

