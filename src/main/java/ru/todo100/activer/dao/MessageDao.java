package ru.todo100.activer.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.*;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MessageItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class MessageDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return MessageItem.class;
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

			Iterator<MessageItem> it = dialogs.iterator();
			while(it.hasNext()) {
				final MessageItem item = it.next();
				if ((item.getAccountFrom() == row[0] && item.getAccountTo() == row[1]) ||
						(item.getAccountFrom() == row[1] && item.getAccountTo() == row[0])) {
					if (item.getAddedDate().getTime().getTime() < messageItem.getAddedDate().getTime().getTime()) {
						it.remove();
					}
				}
			}
			dialogs.add(messageItem);
		}
		return dialogs;
	}
}
