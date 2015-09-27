package ru.todo100.activer.data;

import java.util.List;

/**
 * @author igor
 */
public class IWantData
{
	private Integer        id;
	private String         title;
	private String         description;
	private List<MarkData> markData;

	public List<MarkData> getMarkData()
	{
		return markData;
	}

	public void setMarkData(final List<MarkData> markData)
	{
		this.markData = markData;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(final String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}
}
