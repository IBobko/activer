package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author igor
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Table(name = "can_mark")
@Entity
public class CanMarkItem extends Item
{
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
