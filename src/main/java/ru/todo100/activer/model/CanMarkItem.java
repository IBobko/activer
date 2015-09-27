package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author igor
 */
@Table(name="can_mark")
@Entity
public class CanMarkItem extends Item
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "can_mark_name")
	private String markName;

	public String getMarkName()
	{
		return markName;
	}

	public void setMarkName(final String markName)
	{
		this.markName = markName;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(final Long id)
	{
		this.id = id;
	}

}
