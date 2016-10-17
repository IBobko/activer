package ru.todo100.activer.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "balance")
public class BalanceItem extends Item {
    @Id
    @Column(name = "account_id",insertable = false,updatable = false)
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AccountItem account;

    @Column(name = "sum")
    private BigDecimal sum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(AccountItem account) {
        this.account = account;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
