package ru.todo100.activer.strategy;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
	private static final int IMG_WIDTH  = 250;
	//private static final int IMG_HEIGHT = 170;
	private String         pathToSave;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PhotoService   photoService;

	public static void resize(File original, File dest, String extention)
	{
		try
		{
			if (extention.equals("png"))
			{
				System.out.println(original.getName() + " to " + dest.getName());
				BufferedImage originalImage = ImageIO.read(original);
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				BufferedImage resizeImagePng = resizeImage(originalImage, type);
				ImageIO.write(resizeImagePng, "png", dest);
			}
			if (extention.equals("jpg"))
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
			System.out.println(e.getMessage());
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	{
		int newWidth = IMG_WIDTH;
		int newHeight = (int)(IMG_WIDTH * (double) originalImage.getHeight()  /  (double)originalImage.getWidth());

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
		final String filename = java.util.UUID.randomUUID().toString() + "." + extension;
		return filename;
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
				resize(new File(name),new File(getClass().getResource("/../../").getPath() + getPathToSave() + "/thumb_" + randomFileName), StringUtils.getFilenameExtension(randomFileName));
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			return getPathToSave() + "/" + randomFileName;
		}
		return null;
	}
}
