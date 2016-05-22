package ru.todo100.activer.dao;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MessageItem;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
				))).addOrder(Order.desc("addedDate")).setMaxResults(20).list();
	}

	public Long countNotRed(Integer from,Integer to) {
		return (Long)getCriteria().add(Restrictions.and(Restrictions.eq("accountTo",to),Restrictions.eq("read",0))).add(Restrictions.eq("accountFrom",from)).setProjection(Projections.rowCount()).uniqueResult();
	}

	public List<MessageItem> getDialogSQL(Integer person1, Integer person2) {
		System.out.println("Start: " + System.currentTimeMillis());
		Long start = System.currentTimeMillis();
		Long start2 = System.currentTimeMillis();
		String sql = "SELECT * FROM message where (account_from = " + person1 + " and account_to = " + person2 + ")" +
				" or " +
				"(account_from = " + person2 + " and account_to = " + person1 + ")";


		@SuppressWarnings("unchecked") List<Object[]> rows = getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		System.out.println("SQL QUERY:" + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();

		List<MessageItem> result = new ArrayList<MessageItem>();
		for (Object[] row : rows) {
			MessageItem messageItem = new MessageItem();
			messageItem.setId(((BigDecimal) row[0]).intValue());
			messageItem.setAddedDate(null);
			messageItem.setAccountFrom(null);
			messageItem.setText(null);
			messageItem.setAccountTo(null);
			result.add(messageItem);
		}
		System.out.println("formed: " + (System.currentTimeMillis() - start));
		System.out.println("Total: " + (System.currentTimeMillis() - start2));
		return result;
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
			boolean needForAdd = true;
			while(it.hasNext()) {
				final MessageItem item = it.next();
				if ((item.getAccountFrom() == row[0] && item.getAccountTo() == row[1]) ||
						(item.getAccountFrom() == row[1] && item.getAccountTo() == row[0])) {
					if (item.getAddedDate().getTime().getTime() < messageItem.getAddedDate().getTime().getTime()) {
						it.remove();
					} else {
						needForAdd = false;
					}
				}
			}
			if (needForAdd) {
				dialogs.add(messageItem);
			}
		}
		return dialogs;
	}

	@SuppressWarnings("unchecked")
	public List<MessageItem> searchDialog(final Integer account_id, final String find) {
		final List<MessageItem> dialogs = (List<MessageItem>) getCriteria().add(
				Restrictions.or(
						Restrictions.eq("accountFrom", account_id), Restrictions.eq("accountTo", account_id)
				)).add(Restrictions.ilike("text", find, MatchMode.ANYWHERE)).list();
		final ArrayList<MessageItem> result = new ArrayList<>();
		for (MessageItem messageItem : dialogs) {
			Iterator<MessageItem> it = result.iterator();
			boolean needForAdd = true;
			while (it.hasNext()) {
				final MessageItem item = it.next();
				if ((item.getAccountFrom().equals(messageItem.getAccountFrom()) && item.getAccountTo().equals(messageItem.getAccountTo())) ||
						(item.getAccountFrom().equals(messageItem.getAccountTo()) && item.getAccountTo().equals(messageItem.getAccountFrom()))) {
					if (item.getAddedDate().getTime().getTime() < messageItem.getAddedDate().getTime().getTime()) {
						it.remove();
					} else {
						needForAdd = false;
					}
				}
			}
			if (needForAdd) {
				result.add(messageItem);
			}
		}
		return result;
	}
}
