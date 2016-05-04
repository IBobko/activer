package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.facade.ProfileFacade;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Igor Bobko
 */
@Controller
@RequestMapping("/js")
public class JavaScriptController
{
	@Autowired
	private ProfileFacade profileFacade;

	@ResponseBody
	@RequestMapping("/data.json")
	public Map data() {
		final Map<String,Object> json = new HashMap<>();
		json.put("profile",profileFacade.getCurrentProfile());
		return json;
	}
}
