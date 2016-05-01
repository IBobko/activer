package ru.todo100.activer.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class GiftAddForm {
    public MultipartFile photo;
    public String description;
    public Integer category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
