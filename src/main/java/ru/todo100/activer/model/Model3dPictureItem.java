package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cube3d_model3d_picture")

public class Model3dPictureItem extends Item{
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
	@Column(name = "model3d_id")
	private Long model3d;
	
	@Column(name = "picture_path")
	private String path;
	



	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getModel3d() {
		return model3d;
	}

	public void setModel3d(Long model3d) {
		this.model3d = model3d;
	}

	public String getThumbnail() {
    	int indexOfExtention = this.path.lastIndexOf('.');
    	String Extention = this.path.substring(indexOfExtention + 1, this.path.length()).toLowerCase();
    	String withoutExtention = this.path.substring(0,indexOfExtention);
		
    	String result = withoutExtention + "_thumb" + "." + Extention;
		
		return result;
	}
	
}
