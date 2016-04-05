package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko
 */
@Entity
@Table(name = "wall")
public class WallItem extends Item
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "wall_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "default_gen")
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
