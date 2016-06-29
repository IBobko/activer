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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gift_id", nullable = false)
    private GiftItem gift;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountItem account;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_from_id", nullable = false)
    private AccountItem from;

    @Column(name = "message")
    private String message;

    @NotNull
    @Column(name = "given_date", nullable = false)
    private Calendar givenDate;

    public GiftItem getGift() {
        return gift;
    }

    public void setGift(GiftItem gift) {
        this.gift = gift;
    }

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

    public AccountItem getFrom() {
        return from;
    }

    public void setFrom(AccountItem from) {
        this.from = from;
    }

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(AccountItem account) {
        this.account = account;
    }

}
