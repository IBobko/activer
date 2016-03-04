package ru.todo100.activer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Igor Bobko
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "JpaAttributeTypeInspection"})
@Entity
@Table(name = "account_friend_relation")
public class AccountFriendRelationItem implements Serializable
{
	@Id
	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountItem account;

	@Id
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
}
