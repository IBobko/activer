package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

@SuppressWarnings("unused")
@Entity
@Table(name = "account_friend_relation")
public class AccountFriendRelationItem implements Serializable
{
	@Id
	@OneToOne
	@NotNull
	@JoinColumn(name = "account_id")
	private AccountItem account;

	@Id
	@OneToOne
	@NotNull
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final AccountFriendRelationItem that = (AccountFriendRelationItem) o;
		return account.equals(that.account) && friendAccount.equals(that.friendAccount);
	}

	@Override
	public int hashCode() {
		int result = account.hashCode();
		result = 31 * result + friendAccount.hashCode();
		return result;
	}
}
