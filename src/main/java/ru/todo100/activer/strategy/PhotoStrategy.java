package ru.todo100.activer.strategy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.PhotoDao;

/**
 * @author Igor Bobko
 */
public class PhotoStrategy
{
	private static final int IMG_WIDTH = 250;
	//private static final int IMG_HEIGHT = 170;
	private String     pathToSave;
	@Autowired
	private AccountDao accountService;
	@Autowired
	private PhotoDao   photoService;

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

	public Image TransformColorToTransparency(BufferedImage image, Color c1, Color c2)
	{
		// Primitive test, just an example
		final int r1 = c1.getRed();
		final int g1 = c1.getGreen();
		final int b1 = c1.getBlue();
		final int r2 = c2.getRed();
		final int g2 = c2.getGreen();
		final int b2 = c2.getBlue();
		ImageFilter filter = new RGBImageFilter()
		{
			public final int filterRGB(int x, int y, int rgb)
			{
				int r = (rgb & 0xFF0000) >> 16;
				int g = (rgb & 0xFF00) >> 8;
				int b = rgb & 0xFF;
				if (r >= r1 && r <= r2 &&
								g >= g1 && g <= g2 &&
								b >= b1 && b <= b2)
				{
					// Set fully transparent but keep color
					return rgb & 0xFFFFFF;
				}
				return rgb;
			}
		};

		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	public String saveOriginal(BufferedImage file) throws IOException
	{
		String filename = java.util.UUID.randomUUID().toString() + ".png";
		String name = getClass().getResource("/../../").getPath() + getPathToSave() + "/" + filename;

		PhotoItem photoItem = new PhotoItem();
		photoItem.setAccount(accountService.getCurrentAccount().getId());
		photoItem.setPath(getPathToSave() + "/" + filename);
		photoItem.setType("face");
		photoItem.setAddedDate(new GregorianCalendar());
		photoService.save(photoItem);
		ImageIO.write(file, "png", new File(name));

		resize(new File(name), new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/thumb_" + filename),
		       "png");
		//
		//
		//		if (!file.isEmpty())
		//		{
		//			final String randomFileName = generateRandomName(file.getOriginalFilename());
		//
		//			try
		//			{
		//				byte[] bytes = file.getBytes();
		//				final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
		//				stream.write(bytes);
		//				stream.close();
		//
		//				PhotoItem photoItem = new PhotoItem();
		//				photoItem.setAccount(accountService.getCurrentAccount().getId().intValue());
		//				photoItem.setPath(getPathToSave() + "/" + randomFileName);
		//				photoItem.setType("face");
		//				photoItem.setAddedDate(new GregorianCalendar());
		//				photoService.save(photoItem);
		//				resize(
		//								new File(name),
		//								new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/thumb_" + randomFileName),
		//								StringUtils.getFilenameExtension(randomFileName)
		//				);
		//				resize2(
		//								new File(name),
		//								new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/60x60_" + randomFileName),
		//								StringUtils.getFilenameExtension(randomFileName)
		//				);
		//			}
		//			catch (Exception ignored)
		//			{
		//				/**@TODO Сделать обработку исключения **/
		//			}
		//			return getPathToSave() + "/" + randomFileName;
		//		}
		return filename;
	}

}
