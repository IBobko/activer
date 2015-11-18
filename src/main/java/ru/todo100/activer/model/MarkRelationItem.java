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
@Table(name = "mark_relation")
public class MarkRelationItem extends Item
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
	@Column(name = "mark_id",nullable = false)
	private Integer markId;

	@NotNull
	@Column(name = "relation_id",nullable = false)
	private Integer relationId;

	@NotNull
	@Column(name = "CW",nullable = false)
	private Boolean CW;



	public Integer getMarkId()
	{
		return markId;
	}

	public void setMarkId(final Integer markId)
	{
		this.markId = markId;
	}

	public Integer getRelationId()
	{
		return relationId;
	}

	public void setRelationId(final Integer relationId)
	{
		this.relationId = relationId;
	}

	public Boolean getCW()
	{
		return CW;
	}

	public void setCW(final Boolean CW)
	{
		this.CW = CW;
	}

}
