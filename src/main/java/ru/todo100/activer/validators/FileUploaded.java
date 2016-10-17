package ru.todo100.activer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Documented
@Constraint(validatedBy = FileUploadedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileUploaded {
    String message() default "Файл не передан";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
