package ru.todo100.activer.form;

import javax.validation.constraints.NotNull;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TripForm {
    @NotNull
    private String country;
    @NotNull
    private Integer year;
    @NotNull
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
