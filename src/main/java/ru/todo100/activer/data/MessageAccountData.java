package ru.todo100.activer.data;

import java.io.Serializable;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class MessageAccountData implements Serializable {
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
}
