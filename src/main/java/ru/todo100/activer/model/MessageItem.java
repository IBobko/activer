package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings({"unused"})
@Entity
@Table(name = "message")
public class MessageItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "message_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;
    @NotNull
    @Column(name = "account_from", nullable = false)
    private Integer accountFrom;
    @NotNull
    @Column(name = "account_to", nullable = false)
    private Integer accountTo;
    @NotNull
    @Column(name = "text", nullable = false)
    private String text;
    @NotNull
    @Column(name = "added_date", nullable = false)
    private Calendar addedDate;
    @NotNull
    @Column(name = "read", nullable = false)
    private Integer read;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Calendar getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(final Calendar addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(final Integer accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Integer getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(final Integer accountTo) {
        this.accountTo = accountTo;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
