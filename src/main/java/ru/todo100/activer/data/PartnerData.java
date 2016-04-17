package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PartnerData {
    private String id;
    private String name;
    private String level;
    private String inviter;
    private String invitedCount;
    private String inviterLevel;
    private String networkCount;
    private String earned;
    private String profit;

    public String getInvitedCount() {
        return invitedCount;
    }

    public void setInvitedCount(String invitedCount) {
        this.invitedCount = invitedCount;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getEarned() {
        return earned;
    }

    public void setEarned(String earned) {
        this.earned = earned;
    }

    public String getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(String networkCount) {
        this.networkCount = networkCount;
    }

    public String getInviterLevel() {
        return inviterLevel;
    }

    public void setInviterLevel(String inviterLevel) {
        this.inviterLevel = inviterLevel;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
