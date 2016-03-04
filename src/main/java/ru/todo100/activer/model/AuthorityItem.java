package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority")
@SuppressWarnings(value = "all")
public class AuthorityItem implements java.io.Serializable
{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_username", referencedColumnName = "account_username")
	@Id
	private AccountItem account;

	@NotNull
	@Id
	@Column(name = "authority_role",nullable = false)
	private String role;

	public AccountItem getAccount()
	{
		return account;
	}

	public void setAccount(AccountItem account)
	{
		this.account = account;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

}
