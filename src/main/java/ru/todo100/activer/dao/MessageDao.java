package ru.todo100.activer.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MessageItem;

/**
 * @author Igor Bobko
 */
public class MessageDao extends AbstractDao
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

	/**
	 * Gets last messages
	 *
	 * @param id
	 * @return last messages
	 */
	public List<MessageItem> getLastDialogs(Integer id) {
		final Criterion condition = Restrictions.or(
						Restrictions.eq("accountTo", id),
						Restrictions.eq("accountFrom", id)
		);

		final Criteria criteria = getCriteria().add(condition).addOrder(Order.desc("addedDate"));
		/** @TODO Убрать статическое число **/
		final List<MessageItem> messageItems = criteria.setMaxResults(10).list();
		Collections.reverse(messageItems);;
		return messageItems;
	}

}
