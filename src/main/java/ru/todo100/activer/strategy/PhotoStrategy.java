package ru.todo100.activer.strategy;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.GregorianCalendar;

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
	private String pathToSave;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PhotoService photoService;

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
