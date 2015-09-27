package ru.todo100.activer.service;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.CountryItem;

@SuppressWarnings(value = { "unchecked" })
public class CountryService extends ServiceAbstract{

	@Override
	public Class<? extends Item> getItemClass() {
		// TODO Auto-generated method stub
		return CountryItem.class;
	}


}
