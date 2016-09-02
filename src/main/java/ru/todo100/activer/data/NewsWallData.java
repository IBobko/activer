package ru.todo100.activer.data;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsWallData extends NewsData  {
    List<AttachmentData> attachments;

    public List<AttachmentData> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }
}
