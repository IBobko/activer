package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

@Entity
@Table(name="promocode")
public class PromoCodeItem extends Item {
    @Id
    @Column
    String code;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    AccountItem owner;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    AccountItem used;

    public PromoCodeItem(){super();}

    @Override
    public Integer getId() {
        return code.hashCode();
    }

    @Override
    public void setId(Integer id) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AccountItem getOwner() {
        return owner;
    }

    public void setOwner(AccountItem owner) {
        this.owner = owner;
    }

    public AccountItem getUsed() {
        return used;
    }

    public void setUsed(AccountItem used) {
        this.used = used;
    }


    public String toString() {
        return "[owner: "+ owner + ", used: " + used + ", code: " + code + "]";
    }
}
