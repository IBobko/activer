package ru.todo100.activer.model;

import javax.persistence.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "trip")
public class TripItem {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "trip_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="country_code")
    private CountryItem country;

    @Column(name="trip_year")
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CountryItem getCountry() {
        return country;
    }

    public void setCountry(CountryItem country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
