package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MessageItem;

/**
 * @author Igor Bobko
 */
public class MessageService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return MessageItem.class;
	}

	public List<MessageItem> getMessagesToAccount(Integer accountId)
	{
		final List<MessageItem> messageItems = getCriteria().add(Restrictions.eq("accountTo", accountId)).list();
		return messageItems;
	}

	public List<MessageItem> getMessagesFromAccount(Integer accountFrom)
	{
		final List<MessageItem> messageItems = getCriteria().add(Restrictions.eq("accountFrom", accountFrom)).list();
		return messageItems;
	}


	public List<MessageItem> getLastDialogs(Integer id) {
		Criterion condition = Restrictions.or(
						Restrictions.eq("accountTo", id),
						Restrictions.eq("accountFrom", id)
		);

		final List<MessageItem> messageItems = getCriteria().add(condition).list();

		return messageItems;
	}
}
