package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Igor Bobko
 */
@Entity
@Table(name = "message")
public class MessageItem extends Item
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "account_from")
	private Integer accountFrom;
	@Column(name = "account_to")
	private Integer accountTo;
	@Column(name = "text")
	private String  text;

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
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
