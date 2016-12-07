package ru.todo100.activer.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "payment_credit")
public class PaymentCreditItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "payment_credit_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountItem account;

    @Column(name = "payment_credit_date")
    private Calendar date;

    @Column(name = "payment_credit_sum")
    private BigDecimal paymentCreditSum;

    @Column(name = "payment_credit_description")
    private String paymentCreditDescription;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getPaymentCreditSum() {
        return paymentCreditSum;
    }

    public void setPaymentCreditSum(BigDecimal paymentCreditSum) {
        this.paymentCreditSum = paymentCreditSum;
    }

    public String getPaymentCreditDescription() {
        return paymentCreditDescription;
    }

    public void setPaymentCreditDescription(String paymentCreditDescription) {
        this.paymentCreditDescription = paymentCreditDescription;
    }

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(AccountItem account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
