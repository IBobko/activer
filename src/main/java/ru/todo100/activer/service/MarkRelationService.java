package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.Item;
import ru.todo100.activer.model.MarkRelationItem;

/**
 * @author Igor Bobko
 */

public class MarkRelationService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return MarkRelationItem.class;
	}

	public MarkRelationItem findRelation(Integer relationId, Integer markId, Boolean CW)
	{
		return (MarkRelationItem) getCriteria().add(Restrictions.eq("markId", markId))
		                                       .add(Restrictions.eq("relationId", relationId))
		                                       .add(Restrictions.eq("CW", CW))
		                                       .uniqueResult();
	}

	public MarkRelationItem addRelation(Integer relationId, Integer markId, Boolean CW)
	{
		MarkRelationItem item = new MarkRelationItem();
		item.setMarkId(markId);
		item.setRelationId(relationId);
		item.setCW(CW);
		this.getSession().saveOrUpdate(item);
		return item;
	}

	public void clear(Integer relationId, Boolean CW)
	{
		final List<MarkRelationItem> list = findByRelation(relationId, CW);
		for (MarkRelationItem rel : list)
		{
			getSession().delete(rel);
		}
	}

	public List<MarkRelationItem> findByRelation(Integer relationId, Boolean CW)
	{
		return getCriteria().add(Restrictions.eq("relationId", relationId)).add(Restrictions.eq("CW", CW)).list();
	}

	public List<MarkRelationItem> findByMarkId(Integer id) {
		return getCriteria().add(Restrictions.eq("markId", id)).list();
	}

}
