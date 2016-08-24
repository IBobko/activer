package ru.todo100.activer.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name="BOUGHT_PREMIUM_NEWS")
@PrimaryKeyJoinColumn(name="NEWS_ID")
@DiscriminatorValue("BOUGHT_PREMIUM")
public class BoughtPremiumNewsItem extends NewsItem {
}
