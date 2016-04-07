package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

@Entity
@Table(name = "account_photo")
public class AccountPhotoItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "photo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "photo_id")
    private Integer id;
    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer account;
    @NotNull
    @Column(name = "photo_name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


//	@Column(name = "avatar_path")
//	private String avatarPath;


//	@NotNull
//	@Column(name = "type",nullable = false)
//	private String type;

//	@NotNull
//	@Column(name = "added_date",nullable = false)
//	private Calendar addedDate;

    public Integer getAccount() {
        return account;
    }

    public void setAccount(final Integer account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

//	public Calendar getAddedDate()
//	{
//		return addedDate;
//	}

//	public void setAddedDate(final Calendar addedDate)
//	{
//		this.addedDate = addedDate;
//	}
}
