package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "photo")
public class PhotoItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "photo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    public Integer id;

    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

    @NotNull
    @Column(name = "small_path", nullable = false)
    private String smallPath;

    @NotNull
    @Column(name = "middle_path", nullable = false)
    private String middlePath;

    @Column(name = "album_id")
    private Integer albumId;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getMiddlePath() {
        return middlePath;
    }

    public void setMiddlePath(String middlePath) {
        this.middlePath = middlePath;
    }

    public String getSmallPath() {
        return smallPath;
    }

    public void setSmallPath(String smallPath) {
        this.smallPath = smallPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
