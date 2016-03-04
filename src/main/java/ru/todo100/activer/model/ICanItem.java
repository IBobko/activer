package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "ican")
public class ICanItem extends Item
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "ican_seq", allocationSize = 1)
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
	@ManyToOne
	@JoinColumn(name = "account_id")
	private AccountItem account;

	@NotNull
	@Column(name = "ican_title",nullable = false)
	private String      title;

	@NotNull
	@Column(name = "ican_description",nullable = false)
	private String      description;

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}

	@Column(name = "added_date")
	private Calendar addedDate;

	public AccountItem getAccount()
	{
		return account;
	}

	public void setAccount(final AccountItem account)
	{
		this.account = account;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(final String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}
}
