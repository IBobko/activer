package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.form.IWantForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.model.Item;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings({"unchecked", "JpaQlInspection"})
public class IWantService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return IWantItem.class;
	}

	public IWantItem add(final AccountItem accountItem, final IWantForm iWantForm)
	{
		final IWantItem item = new IWantItem();
		item.setAccount(accountItem);
		item.setDescription(iWantForm.getDescription());
		item.setTitle(iWantForm.getTitle());
		item.setId(iWantForm.getId());
		Session session = getSession();
		session.saveOrUpdate(item);
		return item;
	}

	public List<IWantForm> getAll()
	{
		final Session session = getSession();
		return session.createQuery("from IWantItem c").list();
	}

	public IWantItem get(Integer id)
	{
		final Session session = getSession();
		return (IWantItem) session.get(this.getItemClass(), id);
	}

	public List<IWantItem> getByAccount(final Integer id)
	{
		return getCriteria().add(Restrictions.eq("account.id", id)).addOrder(Order.desc("addedDate")).setMaxResults(10).list();
	}
}
