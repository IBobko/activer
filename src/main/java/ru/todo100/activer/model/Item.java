package ru.todo100.activer.model;

import javax.persistence.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */

@MappedSuperclass
public abstract class Item implements java.io.Serializable
{
//	@Id
//	@SequenceGenerator(name = "default_gen", sequenceName = "account_seq", allocationSize = 1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "default_gen")
//	private Integer id;
//
	public abstract Integer getId();
//	{
//		return id;
//	}
//
	public abstract void setId(Integer id);
//	{
//		this.id = id;
//	}
}
