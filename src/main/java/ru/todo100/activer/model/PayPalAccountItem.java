package ru.todo100.activer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "paypal_account")
public class PayPalAccountItem implements Serializable{
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountItem account;

    @Column(name="paypal_login")
    private String paypalLogin;

    public String getPaypalLogin() {
        return paypalLogin;
    }

    public void setPaypalLogin(String paypalLogin) {
        this.paypalLogin = paypalLogin;
    }

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(AccountItem account) {
        this.account = account;
    }
}
