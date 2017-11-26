package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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
	public String txt(HttpServletResponse response)
	{
		response.setHeader("Content-Disposition","attachment; filename=\"payeer_433665898.txt\"");
		return "1";
	}
}
