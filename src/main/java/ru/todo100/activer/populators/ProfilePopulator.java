package ru.todo100.activer.populators;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.service.PhotoService;

/**
 * @author Igor Bobko
 */
public class ProfilePopulator implements Populator<AccountItem,ProfileData>
{
	@Autowired
	private PhotoService photoService;

	@Override
	public ProfileData populate(final AccountItem accountItem)
	{
		if (accountItem == null)
		{
			return null;
		}
		final PhotoItem facePhoto = photoService.getByAccount(accountItem.getId());

		ProfileData profileData = new ProfileData();
		profileData.setFirstName(accountItem.getFirstName());
		profileData.setLastName(accountItem.getLastName());

		profileData.setId(accountItem.getId());

		if (facePhoto != null) {
			profileData.setFacePhotoUrl(facePhoto.getPath());
		File f = new File(facePhoto.getPath());
		profileData.setFacePhotoUrl(f.getParent() + "/" + "thumb_" + f.getName());
			profileData.setPhoto60x60(f.getParent() + "/" + "60x60_" + f.getName());
	}
		return profileData;
	}
}
