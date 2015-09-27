package ru.todo100.activer.service;

import ru.todo100.activer.model.BlogItem;

public class BlogService extends ServiceAbstract{

	@Override
	public Class<BlogItem> getItemClass() {
		// TODO Auto-generated method stub
		return BlogItem.class;
	}

}
