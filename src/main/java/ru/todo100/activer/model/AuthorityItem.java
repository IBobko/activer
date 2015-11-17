package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority")
@SuppressWarnings(value = "all")
public class AuthorityItem extends Item
{
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_username", referencedColumnName = "account_username")
	private AccountItem account;

	@NotNull
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
