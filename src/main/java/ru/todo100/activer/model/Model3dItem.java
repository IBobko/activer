package ru.todo100.activer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="cube3d_model3d")
public class Model3dItem extends Item{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "model3d_name")
	private String name;

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy="model3d",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Model3dPictureItem> pictures;

	
	@Column(name = "model3d_description")
	private String description;	

	@Column(name = "model3d_price")
	private Float price;
	
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName="id")
	private AccountItem account;
	
	@Column(name = "model3d_file")
	private String file;

	@Column(name = "model3d_download_count")
	private Long downloadCount = 0l;
	
	@Column(name = "category_id")
	private Long category;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Model3dPictureItem> getPictures() {
		return pictures;
	}

	public void setPictures(List<Model3dPictureItem> pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}




	public AccountItem getAccount() {
		return account;
	}

	public void setAccount(AccountItem account) {
		this.account = account;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	public boolean equals(Model3dItem item) {
		if (this.getId().equals(item.getId())) {
			return true;
		}
		return false;
	}
	
	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
	
	public void addMark(String mark){

	}
}
