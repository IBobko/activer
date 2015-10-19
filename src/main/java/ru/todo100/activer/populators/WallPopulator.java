package ru.todo100.activer.populators;

import java.io.File;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.PhotoItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.service.AccountService;
import ru.todo100.activer.service.PhotoService;

/**
 * @author Igor Bobko
 */
public class WallPopulator implements Populator<WallItem, MessageData>
{
	@Autowired
	private AccountService accountService;

	@Autowired
	private PhotoService photoService;

	@Override
	public MessageData populate(final WallItem wallItem)
	{
		final MessageData data = new MessageData();
		data.setText(wallItem.getText());
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		data.setDate(format.format(wallItem.getAddedDate().getTime()));

		final MessageAccountData sender = new MessageAccountData();
		final AccountItem senderItem = accountService.get(wallItem.getSender());
		sender.setFirstName(senderItem.getFirstName());
		sender.setLastName(senderItem.getLastName());

		PhotoItem facePhoto = photoService.getByAccount(senderItem.getId());

		if (facePhoto != null)
		{
			File f = new File(facePhoto.getPath());
			sender.setPhoto60x60(f.getParent() + "/" + "60x60_" + f.getName());
		}
		data.setSender(sender);
		return data;
	}

}
