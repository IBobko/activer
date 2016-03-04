package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author igor
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Table(name = "can_mark")
@Entity
public class CanMarkItem extends Item
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "canmark_seq", allocationSize = 1)
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
	@Column(name = "can_mark_name",nullable = false)
	private String markName;

	public String getMarkName()
	{
		return markName;
	}

	public void setMarkName(final String markName)
	{
		this.markName = markName;
	}
}
