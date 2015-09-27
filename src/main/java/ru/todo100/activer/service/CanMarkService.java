
package ru.todo100.activer.service;

import ru.todo100.activer.model.CanMarkItem;
import ru.todo100.activer.model.Item;

/**
 * @author igor
 */
public class CanMarkService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return CanMarkItem.class;
	}
}
