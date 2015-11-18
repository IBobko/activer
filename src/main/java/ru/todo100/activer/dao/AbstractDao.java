package ru.todo100.activer.dao;

import java.util.GregorianCalendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import ru.todo100.activer.model.DateChanges;
import ru.todo100.activer.model.Item;

abstract public class AbstractDao
{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	abstract public Class<? extends Item> getItemClass();

	public Criteria getCriteria()
	{
		final Session session = getSession();
		session.beginTransaction();
		return session.createCriteria(getItemClass());
	}

	public Session getSession()
	{
		return getSessionFactory().getCurrentSession();
	}

	final public void delete(Integer id)
	{
		Object object = getSession().get(getItemClass(), id);
		if (object != null)
		{
			getSession().delete(object);
		}
	}

	public void save(Item item)
	{
		final Session session = getSession();
		final Transaction tx = session.beginTransaction();
		if (item.getId() != null)
		{
			session.merge(item);
		}
		else
		{
			if (item instanceof DateChanges)
			{
				((DateChanges) item).setCreatedDate(new GregorianCalendar());
			}
			session.persist(item);
		}
		tx.commit();
	}
}
