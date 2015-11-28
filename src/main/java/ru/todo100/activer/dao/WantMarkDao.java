package ru.todo100.activer.dao;

import javax.transaction.Transactional;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.WantMarkItem;

/**
 * @author igor
 */
@Transactional
public class WantMarkDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return WantMarkItem.class;
	}
}

