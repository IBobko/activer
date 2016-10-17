package ru.todo100.activer.model;

import javax.persistence.*;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Entity
@Table(name = "children")
public class ChildrenItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "children_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "children_name")
    private String childrenName;

    @Column(name = "birthdate_year")
    private Integer birthdateYear;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public Integer getBirthdateYear() {
        return birthdateYear;
    }

    public void setBirthdateYear(Integer birthdateYear) {
        this.birthdateYear = birthdateYear;
    }
}
