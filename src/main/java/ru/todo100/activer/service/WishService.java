package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Session;

import ru.todo100.activer.model.CategoryItem;
import ru.todo100.activer.model.WishItem;

@SuppressWarnings(value = { "unchecked" })
public class WishService extends ServiceAbstract{
	public List<WishItem> getAll() {
		List<WishItem> lst = getSession().createQuery("from WishItem c").setMaxResults(10).list();
		return lst;
	}

	@Override
	public Class<WishItem> getItemClass() {
		return WishItem.class;
	}
	
	public WishItem get(Long id) {
		Session session = getSession();
		WishItem obj = (WishItem) session.get(this.getItemClass(), id);
		return obj;
	}
	
	public void save(WishItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}
}
