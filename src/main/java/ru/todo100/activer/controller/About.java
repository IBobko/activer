package ru.todo100.activer.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/about")
public class About {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) {

		return "about/index";
	}

}

