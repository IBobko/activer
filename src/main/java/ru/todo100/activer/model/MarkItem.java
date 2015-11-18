package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "mark")
public class MarkItem extends Item
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
	@Column(name = "mark_name",nullable = false)
	private String  name;

	@NotNull
	@Column(name = "mark_count",nullable = false)
	private Integer count;

	public Integer getCount()
	{
		return count;
	}

	public void setCount(final Integer count)
	{
		this.count = count;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}
}
