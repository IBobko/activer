package ru.todo100.activer.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "news")
public class NewsItem {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "news_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "news_text")
    private String text;

    @Column(name = "news_date")
    private Calendar date;

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
