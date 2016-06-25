package ru.todo100.activer.dao;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.qualifier.WallQualifier;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class WallDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return WallItem.class;
	}

	@SuppressWarnings("unchecked")
	public List<WallItem> getByQualifier(final WallQualifier qualifier) {
		if (qualifier == null) return null;
		if (qualifier.getAccountId() == null) return null;
		final Criteria criteria = getCriteria().add(Restrictions.eq("account.id", qualifier.getAccountId()));

		if (qualifier.getStart() != null) {
			criteria.setFirstResult(qualifier.getStart());
		}

		if (qualifier.getCount()!=null) {
			criteria.setMaxResults(qualifier.getCount());
		}

		if (qualifier.getOrder() == null) {
			criteria.addOrder(Order.desc("addedDate"));
		}
		return criteria.list();
	}
}

