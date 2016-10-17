package ru.todo100.activer.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DreamForm {
    private Integer id;
    private MultipartFile photo;
    private String text;
    private Boolean removePhoto;

    public Boolean getRemovePhoto() {
        return removePhoto;
    }

    public void setRemovePhoto(Boolean removePhoto) {
        this.removePhoto = removePhoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
