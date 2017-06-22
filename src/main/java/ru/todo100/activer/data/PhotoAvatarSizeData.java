package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PhotoAvatarSizeData {
    /**
     * ID фотографии.
     */
    private Integer id;
    /**
     * Фотография отображаемая в сообщениях
     */
    private String photoMini;
    /*
     * Оригинальный размер фото
     */
    private String photoOriginal;
    /*
    * Размер фото, когда его хотят просмотреть, чтоб не слишком большой, как оригинал
    */
    private String photoShowing;
    /*
     *Размер фото видный на автаре
     */
    private String photoAvatar;
    /*
     * Уменьшенный размер фотографий
     */
    private String photoThumbnail;

    private Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoMini() {
        return photoMini;
    }

    public void setPhotoMini(String photoMini) {
        this.photoMini = photoMini;
    }

    public String getPhotoOriginal() {
        return photoOriginal;
    }

    public void setPhotoOriginal(String photoOriginal) {
        this.photoOriginal = photoOriginal;
    }

    public String getPhotoShowing() {
        return photoShowing;
    }

    public void setPhotoShowing(String photoShowing) {
        this.photoShowing = photoShowing;
    }

    public String getPhotoAvatar() {
        return photoAvatar;
    }

    public void setPhotoAvatar(String photoAvatar) {
        this.photoAvatar = photoAvatar;
    }

    public String getPhotoThumbnail() {
        return photoThumbnail;
    }

    public void setPhotoThumbnail(String photoThumbnail) {
        this.photoThumbnail = photoThumbnail;
    }
}
