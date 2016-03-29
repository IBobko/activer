package ru.todo100.activer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MessageItem;

/**
 * @author Igor Bobko
 */
@Transactional
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


	@SuppressWarnings("unchecked")
	public List<MessageItem> getDialog(Integer person1, Integer person2)
	{
		return getCriteria().add(
				Restrictions.or(
						Restrictions.and(
								Restrictions.eq("accountFrom",person1),Restrictions.eq("accountTo",person2)
		),Restrictions.and(
								Restrictions.eq("accountFrom",person2),Restrictions.eq("accountTo",person1)
				))).addOrder(Order.desc("addedDate")).setFetchSize(20).list();
	}

	@SuppressWarnings("unchecked")
	public List<MessageItem> getDialogs(Integer person1)
	{
		final ArrayList<Object[]> result = (ArrayList<Object[]>)getCriteria().add(
				Restrictions.or(
								Restrictions.eq("accountFrom",person1),Restrictions.eq("accountTo",person1)
						)).setProjection(Projections.projectionList()
						.add(Projections.groupProperty("accountFrom"))
						.add(Projections.groupProperty("accountTo"))
				).list();

		final ArrayList<MessageItem> dialogs = new ArrayList<>();

		for (Object[] row: result){
			MessageItem messageItem = (MessageItem)getCriteria().add(Restrictions.and(Restrictions.eq("accountFrom",row[0]),Restrictions.eq("accountTo",row[1]))).addOrder(Order.desc("addedDate")).setMaxResults(1).uniqueResult();
			for (MessageItem item: dialogs) {
				if ((item.getAccountFrom() == row[0] && item.getAccountTo() == row[1]) ||
						(item.getAccountFrom() == row[1] && item.getAccountTo() == row[0])) {
					if (item.getAddedDate().getTime().getTime() < messageItem.getAddedDate().getTime().getTime()) {
						dialogs.remove(item);
					}
				}
			}
			dialogs.add(messageItem);
		}
		return dialogs;
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
