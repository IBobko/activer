package ru.todo100.activer.form;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public class ICanForm
{
	private Integer id;
	private String  title;
	private String  description;
	private String  marks;

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	public String getMarks()
	{
		return marks;
	}

	public void setMarks(final String marks)
	{
		this.marks = marks;
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
