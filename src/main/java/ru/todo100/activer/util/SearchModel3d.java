package ru.todo100.activer.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.service.Model3dService;

public class SearchModel3d {
	@Autowired
	static public Model3dService model3dService;
	public static List<Model3dItem> search(HttpServletRequest request) {
		
		List<Model3dItem> list =  model3dService.getAll();
		return list;
	}

}
