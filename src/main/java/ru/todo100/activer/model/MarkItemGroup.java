package ru.todo100.activer.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MarkItemGroup extends Item
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

	public String Name;
	public Number count;
	public int    fontSize;

	public String getName()
	{
		return Name;
	}

	public Number getCount()
	{
		return count;
	}

	public void setName(String name)
	{
		Name = name;
	}

	public void setCount(Number count)
	{
		this.count = count;
	}

	public int getFontSize()
	{
		return fontSize;
	}

	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

}
