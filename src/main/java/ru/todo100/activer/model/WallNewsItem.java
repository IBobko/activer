package ru.todo100.activer.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name="wall_news")
@PrimaryKeyJoinColumn(name="NEWS_ID")
@DiscriminatorValue("WALL")
public class WallNewsItem extends NewsItem {
    @ManyToOne
    @JoinColumn(name = "wall_id")
    private WallItem wall;

    public WallItem getWall() {
        return wall;
    }

    public void setWall(WallItem wall) {
        this.wall = wall;
    }

}
