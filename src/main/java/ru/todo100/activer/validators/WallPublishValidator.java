package ru.todo100.activer.validators;

import org.springframework.util.StringUtils;
import ru.todo100.activer.data.ReceiveWallData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class WallPublishValidator  implements ConstraintValidator<WallPublish, ReceiveWallData> {
    @Override
    public void initialize(WallPublish wallPublish) {

    }

    @Override
    public boolean isValid(ReceiveWallData receiveWallData, ConstraintValidatorContext constraintValidatorContext) {
        boolean isTextEntered = !StringUtils.isEmpty(receiveWallData.getText());
        boolean isFileUploaded = receiveWallData.getPhoto() != null;
        if (isTextEntered || isFileUploaded) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("text").addConstraintViolation();
        return false;
    }
}
