package ru.todo100.activer.populators;

import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.model.MessageItem;

/**
 * todo Абсолютно не нужный класс на даннм этапе, но удалять жалко
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessagePopulator implements Populator<MessageItem,MessageData>
{
	@Override
	public MessageData populate(final MessageItem messageItem)
	{
		final MessageData messageData = new MessageData();
		return messageData;
	}
}
