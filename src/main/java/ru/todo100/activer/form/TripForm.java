package ru.todo100.activer.form;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class TripForm {
    @NotEmpty(message = "Страна должна быть указана")
    private String country;

    @Pattern(regexp = "\\d{2}/\\d{4}",message = "Год должен быть указан")
    private String year;

    @NotEmpty(message = "Город должен быть указан")
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
}
