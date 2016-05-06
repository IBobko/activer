package ru.todo100.activer.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImage {

	public static void resize(final File original, final File dest, String extention, int width, int height) {
		try{
			if (extention.equals("png")) {
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type, width, height);
				ImageIO.write(resizeImagePng, "png", dest);
			}
			if (extention.equals("jpg")) {
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type, width, height);

				ImageIO.write(resizeImagePng, "jpg", dest);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
    }

	private static BufferedImage resizeImage(final BufferedImage originalImage, final int type, int width, int height) {
		if (originalImage.getWidth() > originalImage.getHeight()) {
			final Double t = ((double) originalImage.getHeight()) * ((double) width) / ((double) originalImage.getWidth());
			height = t.intValue();
		}
    	if (originalImage.getWidth() < originalImage.getHeight()) {
			final Double t = ((double) originalImage.getWidth()) * ((double) height) / ((double) originalImage.getHeight());
			width = t.intValue();
		}

		final BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
    	return resizedImage;
    }
}