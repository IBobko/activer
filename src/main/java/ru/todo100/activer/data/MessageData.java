package ru.todo100.activer.data;

/**
 * @author Igor Bobko
 */
public class MessageData
{
	private Integer id;
	private Integer accountFrom;
	private Integer accountTo;
	private String text;

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
