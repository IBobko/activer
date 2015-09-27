package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.Model3dItem;

@SuppressWarnings(value = { "unchecked" })
public class Model3dService extends ServiceAbstract{
	public List<Model3dItem> getAll() {
		List<Model3dItem> models = getCriteria().list();
		return models;
	}

	@Override
	public Class<Model3dItem> getItemClass() {
		return Model3dItem.class;
	}
	
	public Model3dItem get(Long id) {
		Session session = getSession();
		Model3dItem obj = (Model3dItem) session.get(this.getItemClass(), id);
		return obj;
	}
	
	public void save(Model3dItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}
	
	public List<Model3dItem> getByAccount(AccountItem account) {
		Session session = getSession();
		return session.createCriteria(Model3dItem.class).add( Restrictions.eq("account", account) ).list();
	}
	
	public List<Model3dItem> getPopularityModels(int count) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.ge("price", .1f));
		criteria.setMaxResults(count);
		criteria.addOrder(org.hibernate.criterion.Order.desc("downloadCount"));
		return criteria.list();		
	}
	
	public List<Model3dItem> getPopularityFreeModels(int count) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.le("price", .1f));
		criteria.setMaxResults(count);
		criteria.addOrder(org.hibernate.criterion.Order.desc("downloadCount"));
		return criteria.list();		
	}	
	
}
