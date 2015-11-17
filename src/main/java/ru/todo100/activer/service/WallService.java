package ru.todo100.activer.service;

import java.util.Collections;
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
		List result = this.getCriteria().add(Restrictions.eq("account.id", accountId))
						.addOrder(Order.desc("addedDate")).setMaxResults(10).list();
		return Collections.checkedList(result,WallItem.class);
	}
}

