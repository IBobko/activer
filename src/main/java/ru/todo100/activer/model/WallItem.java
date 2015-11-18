package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko
 */
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "wall")
public class WallItem extends Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id",nullable = false)
	private AccountItem account;

	@NotNull
	@Column(name = "text",nullable = false)
	private String   text;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "added_date",nullable = false)
	private Calendar addedDate;

	@NotNull
	@Column(name = "sender_id",nullable = false)
	private Integer sender;

	public Integer getSender()
	{
		return sender;
	}

	public void setSender(final Integer sender)
	{
		this.sender = sender;
	}

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}

	public AccountItem getAccount()
	{
		return account;
	}

	public void setAccount(final AccountItem account)
	{
		this.account = account;
	}

	public String getText()
	{
		return text;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

}
