package ru.todo100.activer.populators;

import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.MessageItem;

/**
 * @author Igor Bobko
 */
public class MessagePopulator implements Populator<MessageItem,MessageData>
{
	@Override
	public MessageData populate(final MessageItem messageItem)
	{
		final MessageData messageData = new MessageData();
		messageData.setAccountFrom(messageItem.getAccountFrom());
		messageData.setAccountTo(messageItem.getAccountTo());
		messageData.setText(messageItem.getText());
		return messageData;
	}
}
