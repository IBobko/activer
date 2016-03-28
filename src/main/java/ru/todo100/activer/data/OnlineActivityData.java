package ru.todo100.activer.data;

import java.util.Calendar;

/**
 * Stored information about current activity users.
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class OnlineActivityData {

    private String email;
    private Calendar calendar;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
