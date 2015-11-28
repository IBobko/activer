package ru.todo100.activer.dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.todo100.activer.form.IWantForm;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.IWantItem;
import ru.todo100.activer.model.Item;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings({"unchecked", "JpaQlInspection"})
@Transactional
public class IWantDao extends AbstractDao<IWantItem>
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return IWantItem.class;
	}

	@Transactional(readOnly = true)
	public IWantItem get(Integer id)
	{
		final Session session = getSessionFactory().getCurrentSession();
		return  (IWantItem) session.get(getItemClass(), id);
	}

	public IWantItem add(final AccountItem accountItem, final IWantForm iWantForm)
	{
		final IWantItem item = new IWantItem();
		item.setAccount(accountItem);
		item.setDescription(iWantForm.getDescription());
		item.setTitle(iWantForm.getTitle());
		item.setCreatedDate(new GregorianCalendar());
		save(item);
		return item;
	}

	public List<IWantForm> getAll()
	{
		final Session session = getSession();
		return session.createQuery("from IWantItem c").list();
	}

	public List<IWantItem> getByAccount(final Integer id)
	{
		return getCriteria().add(Restrictions.eq("account.id", id)).addOrder(Order.desc("addedDate")).setMaxResults(10).list();
	}
}
