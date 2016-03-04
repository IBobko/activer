package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "mark")
public class MarkItem extends Item
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "mark_seq", allocationSize = 1)
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
