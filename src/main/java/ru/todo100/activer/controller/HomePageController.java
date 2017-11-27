package ru.todo100.activer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
	@RequestMapping("/payeer_433788345.txt")
	public String txt(HttpServletResponse response) throws UnsupportedEncodingException {
		//String fileName = URLEncoder.encode("payeer_433665898.txt", "UTF-8");
		//fileName = URLDecoder.decode(fileName, "ISO8859_1");
		//response.setContentType("application/x-msdownload");
		//response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		return "433788345";
	}
}
