package ru.todo100.activer.data;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MarkItem;

/**
 * @author Igor Bobko
 */
public class MarkSearchData
{
	private AccountItem account;
	private MarkItem    markItem;

	public Boolean getCW()
	{
		return CW;
	}

	public void setCW(final Boolean CW)
	{
		this.CW = CW;
	}

	private Boolean CW;

	public MarkItem getMarkItem()
	{
		return markItem;
	}

	public void setMarkItem(final MarkItem markItem)
	{
		this.markItem = markItem;
	}

	public AccountItem getAccount()
	{
		return account;
	}

	public void setAccount(final AccountItem account)
	{
		this.account = account;
	}
}
