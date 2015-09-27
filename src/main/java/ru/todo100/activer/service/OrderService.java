package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.Session;

import ru.todo100.activer.model.OrderItem;

@SuppressWarnings(value = { "unchecked" })
public class OrderService extends ServiceAbstract{
	@Override
	public Class<OrderItem> getItemClass() {
		return OrderItem.class;
	}
	
	public void save(OrderItem item) {
		Session session = getSession();
		session.saveOrUpdate(item);
	}
	
	public List<OrderItem> getAll() {
		List<OrderItem> lst = getSession().createQuery("from OrderItem c").list();
		return lst;		
	}
	
	public OrderItem get(Long id) {
		Session session = getSession();
		OrderItem obj = (OrderItem) session.get(this.getItemClass(), id);
		return obj;
	}

}
