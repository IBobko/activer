package ru.todo100.activer.data;

import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class NewsData {
    private MessageAccountData accountData;
    private Calendar date;
    private String text;
    private String type;

    public MessageAccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(MessageAccountData accountData) {
        this.accountData = accountData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
