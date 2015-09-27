package ru.todo100.activer.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="cube3d_order")
@SuppressWarnings(value="all")
public class OrderItem extends Item{
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
    @Column(name = "order_date")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Date date;
    
	@ManyToOne
	@JoinColumn(name="model3d_id",referencedColumnName="id")
	private Model3dItem model3d;
    
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName="id")
	private AccountItem account;
    
    @Column(name = "order_status")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long status;
    
    @Transient
    private String[] statusText = {"Not paid","Paid"};

	public Date getDate() {
		return date;
	}

	public Model3dItem getModel3d() {
		return model3d;
	}

	public AccountItem getAccount() {
		return account;
	}

	public Long getStatus() {
		return status;
	}



	public void setDate(Date date) {
		this.date = date;
	}

	public void setModel3d(Model3dItem model3d) {
		this.model3d = model3d;
	}

	public void setAccount(AccountItem account) {
		this.account = account;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getStatusText() {
		return statusText[getStatus().intValue()];
	}
}
