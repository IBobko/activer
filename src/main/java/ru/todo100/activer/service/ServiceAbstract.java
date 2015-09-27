package ru.todo100.activer.service;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.todo100.activer.model.Item;

@Transactional
abstract public class ServiceAbstract {
	@Autowired
	private SessionFactory sessionFactory;
	abstract public Class<? extends Item> getItemClass();
	
	public Criteria getCriteria() {
		Criteria criteria = getSession().createCriteria(getItemClass());
		return criteria;
	}
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
	final public void delete(Long id) {
		Object object = getSession().get(getItemClass(), id);
		if (object!=null) {
			getSession().delete(object);
		}
	}
	
	public void save(Object item) {
		getSession().saveOrUpdate(item);
	}
}
