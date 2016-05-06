package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "account_photo")
public class AccountPhotoItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "ACCOUNT_PHOTO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "photo_id")
    private Integer id;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer account;

    @NotNull
    @Column(name = "photo_name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "photo_name_avatar", nullable = false)
    private String nameAvatar;

    @NotNull
    @Column(name = "photo_name_mini", nullable = false)
    private String nameMini;

    @NotNull
    @Column(name = "photo_name_showing", nullable = false)
    private String nameShowing;

    @NotNull
    @Column(name = "photo_name_thumbnail", nullable = false)
    private String nameThumbnail;

    @NotNull
    @Column(name = "added_date", nullable = false)
    private Calendar addedDate;

    public String getNameThumbnail() {
        return nameThumbnail;
    }

    public void setNameThumbnail(String nameThumbnail) {
        this.nameThumbnail = nameThumbnail;
    }

    public String getNameShowing() {
        return nameShowing;
    }

    public void setNameShowing(String nameShowing) {
        this.nameShowing = nameShowing;
    }

    public String getNameMini() {
        return nameMini;
    }

    public void setNameMini(String nameMini) {
        this.nameMini = nameMini;
    }

    public String getNameAvatar() {
        return nameAvatar;
    }

    public void setNameAvatar(String nameAvatar) {
        this.nameAvatar = nameAvatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

	public Calendar getAddedDate()
	{
		return addedDate;
	}

	public void setAddedDate(final Calendar addedDate)
	{
		this.addedDate = addedDate;
	}
}
