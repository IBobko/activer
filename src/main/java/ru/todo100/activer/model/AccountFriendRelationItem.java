package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Igor Bobko
 */

@Entity
@Table(name = "account_friend_relation")
public class AccountFriendRelationItem extends Item
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "friend_account_id")
	private Integer friendAccountId;

	public Integer getFriendAccountId()
	{
		return friendAccountId;
	}

	public void setFriendAccountId(final Integer friendAccountId)
	{
		this.friendAccountId = friendAccountId;
	}

	public Integer getAccountId()
	{
		return accountId;
	}

	public void setAccountId(final Integer accountId)
	{
		this.accountId = accountId;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}
}
