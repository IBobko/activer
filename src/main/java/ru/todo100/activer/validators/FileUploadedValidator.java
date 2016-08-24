package ru.todo100.activer.validators;

import org.springframework.stereotype.Component;
import ru.todo100.activer.form.UploadFileForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Component
public class FileUploadedValidator implements ConstraintValidator<FileUploaded, UploadFileForm> {
    @Override
    public void initialize(FileUploaded fileUploaded) {}

    @Override
    public boolean isValid(final UploadFileForm uploadFileForm, final ConstraintValidatorContext constraintValidatorContext) {
        if (uploadFileForm.getId()!=null) {
            uploadFileForm.setFile(null);
        } else {
            if (uploadFileForm.getFile() == null) return false;
            if (uploadFileForm.getFile().getOriginalFilename().equals("")) return false;
        }
        return true;
    }
}
