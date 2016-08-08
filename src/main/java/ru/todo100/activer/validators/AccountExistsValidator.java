package ru.todo100.activer.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.form.AccountEmailForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Component
public class AccountExistsValidator implements ConstraintValidator<AccountExists, AccountEmailForm> {
    private AccountDao accountService;

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public void initialize(AccountExists constraintAnnotation) {}

    @Override
    public boolean isValid(AccountEmailForm value, ConstraintValidatorContext context) {
        value.setAccount(accountService.getByEmail(value.getEmail()));
        if (value.getAccount() == null) {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("email").addConstraintViolation();
        }
        return value.getAccount() != null;
    }
}
