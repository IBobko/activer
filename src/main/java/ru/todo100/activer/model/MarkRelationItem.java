package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Igor Bobko
 */
@Entity
@Table(name = "mark_relation")
public class MarkRelationItem extends Item
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "mark_id")
	private Integer markId;
	@Column(name = "relation_id")
	private Integer relationId;
	@Column(name = "CW")
	private Boolean CW;

	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

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
