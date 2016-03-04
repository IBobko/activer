package ru.todo100.activer.model;

import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "iwant")
public class IWantItem extends DateChanges
{
	@Id
	@SequenceGenerator(name = "default_gen", sequenceName = "iwant_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "default_gen")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private AccountItem account;

	@NotNull
	@Column(name = "iwant_title", nullable = false)
	private String title;

/*	@NotNull*/
	@Column(name = "iwant_description", nullable = false)
	private String description;

/*	@NotNull*/
	@Column(name = "added_date",nullable = false)
	private Calendar addedDate;

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}

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
	@PrePersist
		//	@PreUpdate
	void g() {
		System.out.println("--------------------ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
