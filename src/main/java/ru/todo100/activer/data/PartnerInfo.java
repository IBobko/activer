package ru.todo100.activer.data;

import ru.todo100.activer.model.AccountItem;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PartnerInfo {
    private Integer id;
    private String name;
    private Integer level;
    private String inviter;
    private Long invitedCount;
    private Integer inviterLevel;
    private Integer networkCount;
    private BigDecimal earned;
    private BigDecimal profit;
    private String referCode;
    private AccountItem accountItem;

    public AccountItem getAccountItem() {
        return accountItem;
    }

    public void setAccountItem(AccountItem accountItem) {
        this.accountItem = accountItem;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public Long getInvitedCount() {
        return invitedCount;
    }

    public void setInvitedCount(Long invitedCount) {
        this.invitedCount = invitedCount;
    }

    public Integer getInviterLevel() {
        return inviterLevel;
    }

    public void setInviterLevel(Integer inviterLevel) {
        this.inviterLevel = inviterLevel;
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
}
