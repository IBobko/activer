package ru.todo100.activer.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SortType;


@Entity
@Table(name="cube3d_category")

public class CategoryItem extends Item {
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
	
	@Column(name = "category_name")
	private String name;

	@Column(name="parent")
	private Long parent; 
	
    @NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy="parent")
	@Sort(type = SortType.NATURAL)
	@Fetch(FetchMode.SUBSELECT)
/*
    @Cascade({   org.hibernate.annotations.CascadeType.ALL,
	            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	            org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
*/
	private List<CategoryItem> children;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public List<CategoryItem> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryItem> children) {
		this.children = children;
	}
}
