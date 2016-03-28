package ru.todo100.activer.data;

import ru.todo100.activer.PopupMessageType;

import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PacketMessageData {
    private PopupMessageType type;
    private MessageAccountData from;
    private MessageAccountData to;
    private String message;
    private Calendar date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public PopupMessageType getType() {
        return type;
    }

    public void setType(PopupMessageType type) {
        this.type = type;
    }

    public MessageAccountData getFrom() {
        return from;
    }

    public void setFrom(MessageAccountData from) {
        this.from = from;
    }

    public MessageAccountData getTo() {
        return to;
    }

    public void setTo(MessageAccountData to) {
        this.to = to;
    }
}
