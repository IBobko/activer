package ru.todo100.activer.strategy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.PhotoService;

/**
 * @author Igor Bobko
 */
public class PhotoStrategy
{
	private static final int IMG_WIDTH = 250;
	//private static final int IMG_HEIGHT = 170;
	private String pathToSave;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PhotoService photoService;

	public static void resize(File original, File dest, String extension)
	{
		try
		{
			if (extension.equals("png"))
			{
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type);
				ImageIO.write(resizeImagePng, "png", dest);
			}
			if (extension.equals("jpg"))
			{
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type);
				ImageIO.write(resizeImagePng, "jpg", dest);
			}
		}
		catch (IOException e)
		{
			/** @Todo Реализовать исключение **/
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	{
		int newWidth = IMG_WIDTH;
		int newHeight = (int) (IMG_WIDTH * (double) originalImage.getHeight() / (double) originalImage.getWidth());

		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return resizedImage;
	}

	public String getPathToSave()
	{
		return pathToSave;
	}

	public void setPathToSave(final String pathToSave)
	{
		this.pathToSave = pathToSave;
	}

	public String generateRandomName(String originalName)
	{
		final String extension = StringUtils.getFilenameExtension(originalName);
		return java.util.UUID.randomUUID().toString() + "." + extension;
	}

	public String saveFile(MultipartFile file)
	{
		if (!file.isEmpty())
		{
			final String randomFileName = generateRandomName(file.getOriginalFilename());
			String name = getClass().getResource("/../../").getPath() + getPathToSave() + "/" + randomFileName;
			try
			{
				byte[] bytes = file.getBytes();
				final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();

				PhotoItem photoItem = new PhotoItem();
				photoItem.setAccount(accountService.getCurrentAccount().getId().intValue());
				photoItem.setPath(getPathToSave() + "/" + randomFileName);
				photoItem.setType("face");
				photoItem.setAddedDate(new GregorianCalendar());
				photoService.save(photoItem);
				resize(
								new File(name),
								new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/thumb_" + randomFileName),
								StringUtils.getFilenameExtension(randomFileName)
				);
				resize2(
								new File(name),
								new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/60x60_" + randomFileName),
								StringUtils.getFilenameExtension(randomFileName)
				);
			}
			catch (Exception ignored)
			{
				/**@TODO Сделать обработку исключения **/
			}
			return getPathToSave() + "/" + randomFileName;
		}
		return null;
	}

	private static BufferedImage generateMinimal(BufferedImage originalImage, int type,int width)
	{
		final int height = (int) (width * (double) originalImage.getHeight() / (double) originalImage.getWidth());
		final BufferedImage resizedImage = new BufferedImage(width, height, type);
		final Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(originalImage, 0, 0, width, height, null);
		final Area area = new Area(new Rectangle(0,0, width,height));
		area.subtract(new Area(new Ellipse2D.Double(75, 75, 60, 60)));
		graphics.fill(area);
		graphics.dispose();
		return resizedImage;
	}

	public static void resize2(File original, File dest, String extension)
	{
		try
		{
			if (extension.equals("png"))
			{
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = generateMinimal(originalImage, type,60);
				ImageIO.write(resizeImagePng, "png", dest);
			}
			if (extension.equals("jpg"))
			{
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = generateMinimal(originalImage, type, 60);
				ImageIO.write(resizeImagePng, "jpg", dest);
			}
		}
		catch (IOException e)
		{
			/** @Todo Реализовать исключение **/
		}
	}


	public void copyImageToFile(InputStream in, File file)
	{
		try
		{
			final OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
