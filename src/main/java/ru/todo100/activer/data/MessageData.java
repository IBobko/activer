package ru.todo100.activer.data;

import java.util.Calendar;

/**
 * @author Igor Bobko
 */
public class MessageData
{
	private Integer            id;
	private Integer            accountFrom;
	private Integer            accountTo;
	private String             text;
	private MessageAccountData sender;
	private MessageAccountData accountDataTo;
	private String           date;

	public String getDate()
	{
		return date;
	}

	public void setDate(final String date)
	{
		this.date = date;
	}

	public MessageAccountData getAccountDataTo()
	{
		return accountDataTo;
	}

	public void setAccountDataTo(final MessageAccountData accountDataTo)
	{
		this.accountDataTo = accountDataTo;
	}

	public MessageAccountData getSender()
	{
		return sender;
	}

	public void setSender(final MessageAccountData sender)
	{
		this.sender = sender;
	}

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
