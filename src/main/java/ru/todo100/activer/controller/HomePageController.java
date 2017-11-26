package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HomePageController
{
	final public String AUTH_REDIRECT = "redirect:/auth";
	@RequestMapping
	public String index()
	{
		return AUTH_REDIRECT;
	}

	@ResponseBody
	@RequestMapping("/payeer_433665898.txt")
	public String txt()
	{
		return "1";
	}
}
