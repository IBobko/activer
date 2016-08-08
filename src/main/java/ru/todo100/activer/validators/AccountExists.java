package ru.todo100.activer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Documented
@Constraint(validatedBy = AccountExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountExists {
    String message() default "Пользователь не найден";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
