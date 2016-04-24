package ru.todo100.activer.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "payment")
public class PaymentItem {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "payment_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
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
