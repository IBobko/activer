package ru.todo100.activer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "account")
public class AccountItem extends Item
{
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "account_username", referencedColumnName = "account_username")
	List<AuthorityItem> authoritys;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	@Column(name = "account_username")
	private String username;
	@Column(name = "account_password")
	private String password;
	@Column(name = "account_email")
	private String email;
	@Column(name = "account_firstname")
	private String firstName;
	@Column(name = "account_lastname")
	private String lastName;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public void addRole(String role)
	{
		if (authoritys == null)
		{
			authoritys = new ArrayList();
		}
		AuthorityItem item = new AuthorityItem();
		item.setRole(role);
		item.setAccount(this);
		authoritys.add(item);
	}

	public List<AuthorityItem> getAuthoritys()
	{
		return authoritys;
	}

	public void setAuthoritys(final List<AuthorityItem> authoritys)
	{
		this.authoritys = authoritys;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}
}
