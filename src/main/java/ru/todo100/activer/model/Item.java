package ru.todo100.activer.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */

@MappedSuperclass
public class Item implements java.io.Serializable
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
	@PrePersist
	@PreUpdate
	void g() {
		System.out.println(this.getClass());
	}
}
