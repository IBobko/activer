package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TopLineData {
    private MessageAccountData account;
    private String createdData;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageAccountData getAccount() {
        return account;
    }

    public void setAccount(MessageAccountData account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopLineData that = (TopLineData) o;

        return getAccount() != null ? getAccount().equals(that.getAccount()) : that.getAccount() == null;
    }

    @Override
    public int hashCode() {
        return getAccount() != null ? getAccount().hashCode() : 0;
    }

    public String getCreatedData() {
        return createdData;
    }

    public void setCreatedData(String createdData) {
        this.createdData = createdData;
    }
}
