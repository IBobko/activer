package ru.todo100.activer.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "flirt_message")
public class FlirtMessageItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "flirt_message_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "flirt_id")
    private Integer flirtId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "sent_date")
    private Calendar sentDate;

    @Column(name = "message")
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlirtId() {
        return flirtId;
    }

    public void setFlirtId(Integer flirtId) {
        this.flirtId = flirtId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Calendar getSentDate() {
        return sentDate;
    }

    public void setSentDate(Calendar sentDate) {
        this.sentDate = sentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
