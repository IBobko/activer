package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "network_list_cache")
public class NetworkListCacheItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "network_list_cache_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;
    @NotNull

    @Column(name = "owner_account_id", nullable = false)
    private Integer ownerAccountId;

    @Column(name = "account_name", nullable = false)
    private String accountName;
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    @Column(name = "account_level")
    private Integer accountLevel;
    @Column(name = "inviter_name")
    private String inviterName;
    @Column(name = "inviter_level")
    private Integer inviterLevel;
    @Column(name = "invited_count")
    private Integer invitedCount;
    @Column(name = "network_count")
    private Integer networkCount;
    @Column(name = "earned")
    private BigDecimal earned;
    @Column(name = "profit")
    private BigDecimal profit;
    @Column(name = "last_updated")
    private Calendar lastUpdated;

    public Integer getOwnerAccountId() {
        return ownerAccountId;
    }

    public void setOwnerAccountId(Integer ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(Integer accountLevel) {
        this.accountLevel = accountLevel;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public Integer getInviterLevel() {
        return inviterLevel;
    }

    public void setInviterLevel(Integer inviterLevel) {
        this.inviterLevel = inviterLevel;
    }

    public Integer getInvitedCount() {
        return invitedCount;
    }

    public void setInvitedCount(Integer invitedCount) {
        this.invitedCount = invitedCount;
    }

    public Integer getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(Integer networkCount) {
        this.networkCount = networkCount;
    }

    public BigDecimal getEarned() {
        return earned;
    }

    public void setEarned(BigDecimal earned) {
        this.earned = earned;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
