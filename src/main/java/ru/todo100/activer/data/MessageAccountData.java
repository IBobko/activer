package ru.todo100.activer.data;

import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessageAccountData implements Serializable {
    private Number id;
    private String firstName;
    private String lastName;
    private String photo60x60;
    private Boolean online;

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getPhoto60x60() {
        return photo60x60;
    }

    public void setPhoto60x60(final String photo60x60) {
        this.photo60x60 = photo60x60;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Number getId() {
        return id;
    }

    public void setId(final Number id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageAccountData that = (MessageAccountData) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
