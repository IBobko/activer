package ru.todo100.activer.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class UploadFileForm {
    private MultipartFile file;
    private Integer id;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
