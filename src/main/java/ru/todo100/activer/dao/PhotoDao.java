package ru.todo100.activer.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.PhotoItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings("unchecked")
public class PhotoDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return PhotoItem.class;
	}

	public PhotoItem getByAccount(Integer account_id)
	{
		final List<PhotoItem> photos = this.getCriteria()
		                             .add(Restrictions.eq("account", account_id))
		                             .addOrder(Order.desc("addedDate"))
		                             .list();
		if (photos.size() > 0)
		{
			return photos.get(0);
		} else {
			return null;
		}
	}
}
