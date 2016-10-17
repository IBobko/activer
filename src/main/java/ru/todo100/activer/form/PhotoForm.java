package ru.todo100.activer.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PhotoForm {
    private MultipartFile file;
    private Integer id;
    private String name;
    private String description;
    private Integer album;

    public Integer getAlbum() {
        return album;
    }

    public void setAlbum(Integer album) {
        this.album = album;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
