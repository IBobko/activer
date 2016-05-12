package ru.todo100.activer.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.todo100.activer.json.CustomDateSerializer;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessageData implements Serializable {
    /**
     * Идентификатор сообщения в системе
     */
    private Integer id;
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
    private Calendar date;
    /**
     * Прочитан ли сигнал получателем
     */
    private Boolean read;
    /**
     * Собеседник, противоположная сторона. Часто может совпадать либо с from.id, либо с to.id
     * Также может содержать ID диалога
     */
    private Integer interlocutor;

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Integer getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Integer interlocutor) {
        this.interlocutor = interlocutor;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Calendar getDate() {
        return date;
    }

    public void setDate(final Calendar date) {
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

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
