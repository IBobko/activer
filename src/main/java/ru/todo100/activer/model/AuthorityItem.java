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

@Entity
@Table(name="cube3d_authority")
@SuppressWarnings(value="all")
public class AuthorityItem extends Item{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_username",referencedColumnName="account_username")
	AccountItem account;
	
	@Column(name="authority_role")
	String role;

	
	public AccountItem getAccount() {
		return account;
	}

	public void setAccount(AccountItem account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
