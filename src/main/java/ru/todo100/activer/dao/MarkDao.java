package ru.todo100.activer.dao;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MarkItem;
@Transactional
public class MarkDao extends AbstractDao
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return MarkItem.class;
	}

	public MarkItem findMark(String mark)
	{
		return (MarkItem) this.getCriteria().add(Restrictions.eq("name", mark)).uniqueResult();
	}

	public MarkItem findMark(Integer mark)
	{
		return (MarkItem) this.getCriteria().add(Restrictions.eq("id", mark)).uniqueResult();
	}

	public MarkItem addMark(String markName) {
		MarkItem item = new MarkItem();
		item.setCount(0);
		item.setName(markName);
		this.getSession().saveOrUpdate(item);
		return item;
	}

}
