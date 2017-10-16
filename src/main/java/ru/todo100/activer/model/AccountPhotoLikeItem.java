package ru.todo100.activer.model;

import javax.persistence.*;


@Entity
@Table(name = "account_photo_like")
public class AccountPhotoLikeItem {

    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "account_photo_like_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_made_like", referencedColumnName = "id", nullable = false)
    private AccountItem accountMadeLike;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_photo_id", referencedColumnName = "photo_id", nullable = false)
    private AccountPhotoItem photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountItem getAccountMadeLike() {
        return accountMadeLike;
    }

    public void setAccountMadeLike(AccountItem accountMadeLike) {
        this.accountMadeLike = accountMadeLike;
    }

    public AccountPhotoItem getPhoto() {
        return photo;
    }

    public void setPhoto(AccountPhotoItem photo) {
        this.photo = photo;
    }
}
