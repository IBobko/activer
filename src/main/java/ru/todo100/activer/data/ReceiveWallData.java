package ru.todo100.activer.data;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class ReceiveWallData {
    private Integer id;
    private String text;
    private MultipartFile photo;

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
