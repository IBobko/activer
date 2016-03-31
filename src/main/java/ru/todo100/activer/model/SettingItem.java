package ru.todo100.activer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "setting")
public class SettingItem implements Serializable {
    @Id
    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    @Id
    @NotNull
    @Column(name = "key", nullable = false)
    private String key;
    @Column(name = "value")
    private String value;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SettingItem that = (SettingItem) o;
        return accountId.equals(that.accountId) && key.equals(that.key);
    }

    @Override
    public int hashCode() {
        int result = accountId.hashCode();
        result = 31 * result + key.hashCode();
        return result;
    }
}
