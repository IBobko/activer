package ru.todo100.activer.form;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.validators.AccountExists;


/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@AccountExists
public class AccountEmailForm {

    private String email;

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(AccountItem account) {
        this.account = account;
    }

    private AccountItem account;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
