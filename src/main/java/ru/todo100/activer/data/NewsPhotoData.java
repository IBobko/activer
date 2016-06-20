package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsPhotoData extends NewsData {
    private String photoShowing;
    private String photoThumbnail;

    public String getPhotoShowing() {
        return photoShowing;
    }

    public void setPhotoShowing(String photoShowing) {
        this.photoShowing = photoShowing;
    }

    public String getPhotoThumbnail() {
        return photoThumbnail;
    }

    public void setPhotoThumbnail(String photoThumbnail) {
        this.photoThumbnail = photoThumbnail;
    }
}
