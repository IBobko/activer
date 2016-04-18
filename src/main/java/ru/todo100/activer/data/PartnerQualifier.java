package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PartnerQualifier extends Qualifier {
    private Integer ownerAccountId;
    private Integer accountId;

    public Integer getOwnerAccountId() {
        return ownerAccountId;
    }

    public void setOwnerAccountId(Integer ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
