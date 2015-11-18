package ru.todo100.activer.dao;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.WantMarkItem;

/**
 * @author igor
 */
public class WantMarkDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return WantMarkItem.class;
	}
}

