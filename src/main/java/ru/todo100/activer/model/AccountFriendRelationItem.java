package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountItem account;

	@OneToOne
	@JoinColumn(name = "friend_account_id")
	private AccountItem friendAccount;

	public AccountItem getAccount()
	{
		return account;
	}

	public void setAccount(final AccountItem account)
	{
		this.account = account;
	}

	public AccountItem getFriendAccount()
	{
		return friendAccount;
	}

	public void setFriendAccount(final AccountItem friendAccount)
	{
		this.friendAccount = friendAccount;
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
