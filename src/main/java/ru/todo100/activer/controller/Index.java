package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class Index
{
	final public String AUTH_REDIRECT = "redirect:/auth";
	@RequestMapping
	public String index()
	{
		return AUTH_REDIRECT;
	}
}
