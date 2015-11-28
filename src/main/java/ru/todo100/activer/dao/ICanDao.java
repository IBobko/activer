package ru.todo100.activer.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.form.ICanForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.ICanItem;
import ru.todo100.activer.model.Item;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Transactional
public class ICanDao extends AbstractDao
{

	@Override
	public Class<? extends Item> getItemClass()
	{
		return ICanItem.class;
	}

	public ICanItem add(final AccountItem accountItem, final ICanForm iCanForm)
	{
		final ICanItem item = new ICanItem();
		item.setAccount(accountItem);
		item.setDescription(iCanForm.getDescription());
		item.setTitle(iCanForm.getTitle());
		item.setId(iCanForm.getId());
		Session session = getSession();
		session.saveOrUpdate(item);
		return item;
	}

	public List<ICanItem> getAll() {
		final Session session = getSession();
		final List<ICanItem> cans = session.createQuery("from ICanItem c").list();
		return cans;
	}

	public ICanItem get(Integer id) {
		final Session session = getSession();
		return  (ICanItem) session.get(this.getItemClass(), id);
	}


	public List<ICanItem> getByAccount(Integer id) {
		return getCriteria().add(Restrictions.eq("account.id", id)).addOrder(Order.desc("addedDate")).setMaxResults(10).list();
	}
}
