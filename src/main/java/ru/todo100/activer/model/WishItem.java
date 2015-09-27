package ru.todo100.activer.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cube3d_wish")
@SuppressWarnings(value="all")
public class WishItem extends Item{
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
	@Column(name = "wish_date")
	private Date date;

	@Column(name = "wish_text")
	private String text;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName="id")
	private AccountItem account;

	public Date getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public AccountItem getAccount() {
		return account;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setAccount(AccountItem account) {
		this.account = account;
	}
}
