package ru.todo100.activer.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings(value = { "unused" })
public class ResizeImage {
	private static final int IMG_WIDTH = 170;
	private static final int IMG_HEIGHT = 170;
 
	public static void resize(File original, File dest,String extention){
		try{
			if (extention.equals("png")) {
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type);
				ImageIO.write(resizeImagePng, "png", dest);
			}
			if (extention.equals("jpg")) {
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type);
				ImageIO.write(resizeImagePng, "jpg", dest);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
    }
 
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
    	
    	int newHeight = IMG_HEIGHT;
    	int newWidth = IMG_WIDTH;
    	
    	
    	
    	
    	if (originalImage.getWidth() > originalImage.getHeight()) {
    		Double t = ((double)originalImage.getHeight()) / ((double)originalImage.getWidth()) * IMG_HEIGHT;
    		newHeight = t.intValue();
    	}
    	if (originalImage.getWidth() < originalImage.getHeight()) {
    		Double t = ((double)originalImage.getWidth()) / ((double)originalImage.getHeight()) * IMG_WIDTH;
    		newWidth = t.intValue();
    	}
    	
    	
    	BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
    	Graphics2D g = resizedImage.createGraphics();
    	g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
    	g.dispose();
    	return resizedImage;
    }
 
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
    	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);
	 
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	 
		return resizedImage;
    }	
}