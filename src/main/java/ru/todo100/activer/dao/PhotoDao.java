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
public class PhotoDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return AccountPhotoItem.class;
	}

	@Transactional
	public AccountPhotoItem getByAccount(final Integer account_id)
	{
		return (AccountPhotoItem)getCriteria()
				.add(Restrictions.eq("account",account_id))
				.addOrder(Order.desc("id"))
				.setMaxResults(1).uniqueResult();
	}

}
