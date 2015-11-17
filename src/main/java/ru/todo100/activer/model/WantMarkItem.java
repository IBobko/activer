package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "want_mark")
public class WantMarkItem extends Item
{
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
