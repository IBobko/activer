package ru.todo100.activer.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "payment_debit")
public class PaymentDebitItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "payment_debit_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "payment_debit_date")
    private Calendar date;

    @Column(name = "payment_debit_sum")
    private BigDecimal paymentDebitSum;

    @Column(name = "payment_debit_description")
    private String paymentDebitDescription;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getPaymentDebitSum() {
        return paymentDebitSum;
    }

    public void setPaymentDebitSum(BigDecimal paymentDebitSum) {
        this.paymentDebitSum = paymentDebitSum;
    }

    public String getPaymentDebitDescription() {
        return paymentDebitDescription;
    }

    public void setPaymentDebitDescription(String paymentDebitDescription) {
        this.paymentDebitDescription = paymentDebitDescription;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
