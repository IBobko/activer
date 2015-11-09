package ru.todo100.activer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.model.Model3dItem;
import ru.todo100.activer.model.Model3dPictureItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.Model3dService;
import ru.todo100.activer.service.ParameterService;


public class SaveModel3d {	
	@Autowired
	public Model3dService model3dService;
	
//	@Autowired
//	Startup initApplication;
	
	@Autowired
	public AccountService accountService;
	public ServletContext servletContext;
	public InputError inputErrors = new InputError();
	@Autowired
	ParameterService parameterService;

	public Long save(HttpServletRequest request,Part images[],Part file) throws InputError {
		inputErrors.getErrors().clear();
		Model3dItem item = generateModel3dItem(request,file);
		List<Model3dPictureItem> pictures = generatePictures(images);
		if (inputErrors.getErrors().size() != 0) {
			throw inputErrors;
		}
		model3dService.save(item);
		/*
		setParentIdForPictures(pictures, item.getId());
		item.setPictures(pictures);
		model3dService.save(item);
		initApplication.setServletContext(servletContext);
		*/
		return item.getId();
	}
	
	public List<Model3dPictureItem> generatePictures(Part images[]) {
		List<Model3dPictureItem> pictures = new ArrayList<Model3dPictureItem>();
		for (Part image : images) {
			String extension = null;
			String contentType = image.getContentType(); 
			
			if (contentType.equals("image/jpeg")) {
				extension = ".jpg";
			} 
			
			if (contentType.equals("image/png")) {
				extension = ".png";
			}
			
			if (extension == null) {
				inputErrors.addError("Wrong file!");
			}
			
			String imageName = accountService.getCurrentAccount().getId() + 
					"_" + RandomStringUtils.random(8, true, true);
			
			imageName += extension;
			
			
			String Path = parameterService.getValue("picture_path");
			
			try {
				image.write(Path + "/" + imageName);
			} catch(IOException e) {
				inputErrors.addError("Error write file into server");
			}
			Model3dPictureItem picture = new Model3dPictureItem();
			picture.setPath(imageName);
			pictures.add(picture);	
		}
		return pictures;	
	}
	
	public Model3dItem generateModel3dItem(HttpServletRequest request,Part file) {
		String name = request.getParameter("name").trim();
		
		if (name.isEmpty()) {
			inputErrors.addError("Name is empty");
		}
		
		Long category = 0L;
		try {
			category = Long.parseLong(request.getParameter("category"));
		} catch(NumberFormatException e) {
			inputErrors.addError("Category is not number");
		}
		
		if (category == 0) {
			inputErrors.addError("Category is not selected");
		}
		
		String description = request.getParameter("description").trim();
		if (description.isEmpty()) {
			inputErrors.addError("Description is empty");
		}
		
		Float price = 0f;
		try {
			price = Float.parseFloat(request.getParameter("price"));
		} catch(NumberFormatException e) {
			inputErrors.addError("Price is not number");
		}
		
		if (file.getSize() == 0) {
			inputErrors.addError("The model file is not selected.");
		}
		
		String filename = generateFileName(file.getName());
				
		String Path = parameterService.getValue("stl_path");
		
		try {
			file.write(Path + filename);
		} catch (IOException e) {
			// nothing=)
		}
		
		Model3dItem item = new Model3dItem();
		item.setDescription(description);
		item.setName(name);
		item.setPrice(price);
		item.setCategory(category);
		item.setAccount(accountService.getCurrentAccount());
		item.setFile(filename);
		
		String marks = request.getParameter("marks");
		String[] mark = marks.split(",");
		for (String s: mark){
			item.addMark(s.trim());
		}
		return item;
	}
	
	public void setParentIdForPictures(List<Model3dPictureItem> pictures, Long id) {
		for (Model3dPictureItem picture : pictures) {
			picture.setModel3d(id);
		}
	}
	
	public String generateFileName(String original) {
		String fileName = accountService.getCurrentAccount().getId() + 
		"_" + RandomStringUtils.random(8, true, true) + "_" + original;
		return fileName;
	}
}
