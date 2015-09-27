package ru.todo100.activer.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.ParameterItem;

@SuppressWarnings(value = { "unchecked" })
public class ParameterService extends ServiceAbstract{
	@Override
	public Class<ParameterItem> getItemClass() {
		return ParameterItem.class;
	}
	
	public String getValue(String name) {
		Criteria criteria = getCriteria().add(Restrictions.eq("name", name));
		ParameterItem item = (ParameterItem)criteria.list().get(0);
		return item.getValue();
	}
}
