package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import ru.todo100.activer.model.CategoryItem;

@SuppressWarnings(value = { "unchecked" })
public class CategoryService extends ServiceAbstract{
	public List<CategoryItem> getAll() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(CategoryItem.class);
		List<CategoryItem> categories = criteria.list();
		return categories;
	}

	@Override
	public Class<CategoryItem> getItemClass() {
		return CategoryItem.class;
	}
	
	public CategoryItem get(Long id) {
		Session session = getSession();
		CategoryItem obj = (CategoryItem) session.get(this.getItemClass(), id);
		return obj;
	}
	
	public List<CategoryItem> getCategories(Long parent) {
		Session session = getSession();
		List<CategoryItem> categories = session.
				createQuery("from CategoryItem c where c.parent = :parent").
				setParameter("parent", parent).list();
		return categories;
	}

	public void save(CategoryItem category) {
		Session session = getSession();
		session.saveOrUpdate(category);
	}
}
