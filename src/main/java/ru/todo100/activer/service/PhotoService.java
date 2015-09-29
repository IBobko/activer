package ru.todo100.activer.service;

import ru.todo100.activer.model.Item;
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
}
