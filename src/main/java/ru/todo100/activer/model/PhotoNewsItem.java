package ru.todo100.activer.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

@Entity
@Table(name="photo_news")
@PrimaryKeyJoinColumn(name="NEWS_ID")
@DiscriminatorValue("PHOTO")
public class PhotoNewsItem extends NewsItem  {

}
