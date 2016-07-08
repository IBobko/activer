package ru.todo100.activer.data;

import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessageData implements Serializable {
    /**
     * Идентификатор сообщения в системе
     */
    private Number id;
    /**
     * Тело сигнала
     */
    private String message;
    /**
     * От кого пришел сигнал
     */
    private MessageAccountData from;
    /**
     * Кому предназначается сигнал
     */
    private MessageAccountData to;
    /**
     * Дата сигнала
     */
    private String date;
    /**
     * Прочитан ли сигнал получателем
     */
    private Boolean read;

    private String attachmentFile;

    public String getAttachmentHtml() {
        return attachmentHtml;
    }

    public void setAttachmentHtml(String attachmentHtml) {
        this.attachmentHtml = attachmentHtml;
    }

    private String attachmentHtml;
    /**
     * Собеседник, противоположная сторона. Часто может совпадать либо с from.id, либо с to.id
     * Также может содержать ID диалога
     */
    private Number interlocutor;

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Number getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Number interlocutor) {
        this.interlocutor = interlocutor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public MessageAccountData getTo() {
        return to;
    }

    public void setTo(final MessageAccountData to) {
        this.to = to;
    }

    public MessageAccountData getFrom() {
        return from;
    }

    public void setFrom(final MessageAccountData from) {
        this.from = from;
    }

    public Number getId() {
        return id;
    }

    public void setId(final Number id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
