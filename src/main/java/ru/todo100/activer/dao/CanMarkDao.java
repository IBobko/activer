
package ru.todo100.activer.dao;

import ru.todo100.activer.model.CanMarkItem;
import ru.todo100.activer.model.Item;

/**
 * @author igor
 */
public class CanMarkDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return CanMarkItem.class;
	}
}
