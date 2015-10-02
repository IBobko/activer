package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MarkItem;
import ru.todo100.activer.model.PhotoItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public class PhotoService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return PhotoItem.class;
	}

	public PhotoItem getByAccount(Integer account_id) {
		List<PhotoItem> photos = this.getCriteria().add(Restrictions.eq("account", account_id)).addOrder(Order.desc("addedDate")).list();
		return photos.get(0);

	}
}
