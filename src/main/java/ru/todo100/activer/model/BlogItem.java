package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cube3d_blog")
@SuppressWarnings(value="all")
public class BlogItem extends Item{
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    @Column(name = "account_id")	
	private Long account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
    @Column(name = "blog_title")	
	private String title;
	
    @Column(name = "blog_text")
	private String text;



	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}
    
}
