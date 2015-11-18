package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko
 */
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "message")
public class MessageItem extends Item
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
	@Column(name = "account_from",nullable = false)
	private Integer accountFrom;

	@NotNull
	@Column(name = "account_to",nullable = false)
	private Integer accountTo;

	@NotNull
	@Column(name = "text",nullable = false)
	private String  text;

	@NotNull
	@Column(name = "added_date",nullable = false)
	private Calendar addedDate;

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}

	public Integer getAccountFrom()
	{
		return accountFrom;
	}

	public void setAccountFrom(final Integer accountFrom)
	{
		this.accountFrom = accountFrom;
	}

	public Integer getAccountTo()
	{
		return accountTo;
	}

	public void setAccountTo(final Integer accountTo)
	{
		this.accountTo = accountTo;
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
