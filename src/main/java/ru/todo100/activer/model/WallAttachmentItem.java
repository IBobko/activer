package ru.todo100.activer.model;

import javax.persistence.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "wall_attachment")
public class WallAttachmentItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "wall_attachment_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "wall_id")
    public WallItem wall;

    @Column(name = "photo")
    public String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public WallItem getWall() {
        return wall;
    }

    public void setWall(WallItem wall) {
        this.wall = wall;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
