package ru.todo100.activer.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "news")
public class NewsItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "news_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "news_text")
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "news_type")
    private String type;


    @Column(name = "news_date")
    private Calendar date;

    @Column(name = "account_id")
    private Integer accountId;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
