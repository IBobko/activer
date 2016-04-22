package ru.todo100.activer.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "admin_account_list_cache")
public class AdminAccountListCacheItem implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountItem accountItem;

    @Column(name = "network_count")
    private Integer networkCount;

    @Column(name = "inviter_name")
    private String inviterName;

    @Column(name = "inviter_id")
    private Integer inviterId;

    public Integer getInviterId() {
        return inviterId;
    }

    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public Integer getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(Integer networkCount) {
        this.networkCount = networkCount;
    }

    public AccountItem getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(AccountItem accountItem) {
        this.accountItem = accountItem;
    }

}
