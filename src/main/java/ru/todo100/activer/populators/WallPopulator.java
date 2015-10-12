package ru.todo100.activer.populators;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.service.AccountService;

/**
 * @author Igor Bobko
 */
public class WallPopulator implements Populator<WallItem,MessageData>
{
	@Autowired
	private AccountService accountService;

	@Override
	public MessageData populate(final WallItem wallItem)
	{
		final MessageData data = new MessageData();
		data.setText(wallItem.getText());
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		data.setDate(format.format(wallItem.getAddedDate().getTime()));

		final MessageAccountData sender = new MessageAccountData();
		final AccountItem senderItem = accountService.get(wallItem.getAccountId());
		sender.setFirstName(senderItem.getFirstName());
		sender.setLastName(senderItem.getLastName());
		data.setSender(sender);
		return data;
	}
}
