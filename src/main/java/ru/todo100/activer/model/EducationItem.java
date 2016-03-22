package ru.todo100.activer.model;

import javax.persistence.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Table(name = "education")
@Entity
public class EducationItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "education_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private CountryItem country;

    @Column(name = "city")
    private String city;

    @Column(name = "university")
    private String university;
    @Column(name = "faculty")

    private String faculty;
    @Column(name = "end_year")
    private Integer endYear;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public CountryItem getCountry() {
        return country;
    }

    public void setCountry(CountryItem country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
