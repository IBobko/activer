package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "want_mark")
public class WantMarkItem extends Item
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "wantmark_seq", allocationSize = 1)
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
	@Column(name = "want_mark_name",nullable = false)
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
