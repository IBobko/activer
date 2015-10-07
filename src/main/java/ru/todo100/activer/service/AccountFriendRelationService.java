package ru.todo100.activer.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import ru.todo100.activer.model.AccountFriendRelationItem;
import ru.todo100.activer.model.Item;

/**
 * @author Igor Bobko
 */
public class AccountFriendRelationService extends ServiceAbstract
{
	@Override
	public Class<? extends Item> getItemClass()
	{
		return AccountFriendRelationItem.class;
	}

	public List<AccountFriendRelationItem> getFriends(Integer accountId)
	{
		return getCriteria().add(Restrictions.eq("accountId", accountId) ).list();
	}
}
