package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "account_gift")
public class AccountGiftItem {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "account_gift_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;


    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "account_from_id")
    private Integer accountFormId;

    @Column(name = "message")
    private String message;

    @NotNull
    @Column(name = "given_date", nullable = false)
    private Calendar givenDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Calendar givenDate) {
        this.givenDate = givenDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAccountFormId() {
        return accountFormId;
    }

    public void setAccountFormId(Integer accountFormId) {
        this.accountFormId = accountFormId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


}
