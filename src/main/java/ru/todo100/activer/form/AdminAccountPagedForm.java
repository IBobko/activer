package ru.todo100.activer.form;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountPagedForm extends PagedForm {
    private Boolean online;
    private Integer accountType;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
