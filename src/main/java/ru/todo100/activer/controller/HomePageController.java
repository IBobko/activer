package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomePageController
{
	final public String AUTH_REDIRECT = "redirect:/auth";
	@RequestMapping
	public String index()
	{
		String hello = "hello";
		System.out.println(hello + " " + "World" + " Image");

		return AUTH_REDIRECT;
	}
}


