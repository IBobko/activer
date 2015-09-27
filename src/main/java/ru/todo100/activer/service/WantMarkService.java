package ru.todo100.activer.service;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.WantMarkItem;

/**
 * @author igor
 */
public class WantMarkService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return WantMarkItem.class;
	}
}

