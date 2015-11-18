package ru.todo100.activer.model;

import java.util.Calendar;

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
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name="photo")
public class PhotoItem extends Item
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
	@Column(name = "account_id",nullable = false)
	private Integer  account;

	@NotNull
	@Column(name = "path",nullable = false)
	private String   path;

	@NotNull
	@Column(name = "type",nullable = false)
	private String   type;

	@NotNull
	@Column(name = "added_date",nullable = false)
	private Calendar addedDate;

	public Integer getAccount()
	{
		return account;
	}

	public void setAccount(final Integer account)
	{
		this.account = account;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(final String path)
	{
		this.path = path;
	}

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}
}
