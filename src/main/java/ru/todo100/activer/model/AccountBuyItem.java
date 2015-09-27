package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="cube3d_account_buy")
@Entity
public class AccountBuyItem extends Item{
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
	@ManyToOne
	@JoinColumn(name="account_id",referencedColumnName="id")
	private AccountItem account;
    
	@ManyToOne
	@JoinColumn(name="model3d_id",referencedColumnName="id")
	private Model3dItem model3d;


	public AccountItem getAccount() {
		return account;
	}

	public Model3dItem getModel3d() {
		return model3d;
	}

	public void setAccount(AccountItem account) {
		this.account = account;
	}

	public void setModel3d(Model3dItem model3d) {
		this.model3d = model3d;
	}	
}