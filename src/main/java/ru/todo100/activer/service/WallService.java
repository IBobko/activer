package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.WallItem;

/**
 * @author Igor Bobko
 */
public class WallService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return WallItem.class;
	}

	@SuppressWarnings("unchecked")
	public List<WallItem> getAllByAccount(Integer accountId) {
		return this.getCriteria().add(Restrictions.eq("accountId", accountId))
						.addOrder(Order.desc("addedDate")).setMaxResults(10)
		           .list();
	}
}
