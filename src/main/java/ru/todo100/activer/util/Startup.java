package ru.todo100.activer.util;

import java.io.File;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ServletContextAware;

public class Startup implements ServletContextAware {
	@Value("${pic.thumbPrefix}")
	private String THUMB_PREFIX;
	
	@Value("${pic.path}")
	private String PICTURE_PATH;

	public Startup() {
		
	}
	
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		/* @TODO На случай, если я изменю способ получения этой переменной */ 
		String picturePath = this.PICTURE_PATH; 
		
		if (picturePath != null) {
			picturePath = servletContext.getRealPath(picturePath);
			final File folder = new File(picturePath);
			//this.checkModelPictures(folder);
		}
	}
	
	public void checkModelPictures(final File folder) {
	    for (final File file : folder.listFiles()) {
	        if (file.isDirectory()) {
	        	/* @TODO пропускаем в этом случае */
	        	continue;
	        } else {
	        	String fileName = file.getName();
	        	int indexOfExtention = fileName.lastIndexOf('.');
	        	String Extention = fileName.substring(indexOfExtention + 1, fileName.length()).toLowerCase();
	        	String withoutExtention = fileName.substring(0,indexOfExtention);	        	
	        	
	        	final File thumbFile = new File(file.getParent() + "/" + withoutExtention + THUMB_PREFIX + '.' + Extention);
	        	if (!thumbFile.exists() && fileName.indexOf(THUMB_PREFIX) == -1) {
	        		ResizeImage.resize(file, thumbFile,Extention);	
	        	}
	        }
	    }	
	}
}
