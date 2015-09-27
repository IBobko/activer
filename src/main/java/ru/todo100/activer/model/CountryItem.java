package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="cube3d_country")
@Entity
public class CountryItem extends Item{
	@Id
    @Column(name = "country_code")
	private String code;

    @Column(name = "country_name")	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}