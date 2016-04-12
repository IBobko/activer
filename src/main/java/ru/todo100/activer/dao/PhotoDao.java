package ru.todo100.activer.dao;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.model.Item;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings("unchecked")
@Transactional
public class PhotoDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return AccountPhotoItem.class;
	}

	public AccountPhotoItem getByAccount(Integer account_id)
	{
		final List<AccountPhotoItem> photos = this.getCriteria()
				.add(Restrictions.eq("account", account_id)).addOrder(Order.desc("addedDate")).list();
		if (photos.size() > 0)
		{
			return photos.get(0);
		} else {
			return null;
		}
	}

}
