package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "gift")
public class GiftItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "gift_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "category_id")
    private Integer category;

    @Column(name = "gift_file")
    private String file;

    @Column(name = "gift_name")
    private String name;

    @NotNull
    @Column(name = "gift_enabled", nullable = false, columnDefinition = "NUMBER DEFAULT 1 NOT NULL")
    private Boolean enabled;

    @NotNull
    @Column(name = "gift_cost", nullable = false, columnDefinition = "NUMBER DEFAULT 1 NOT NULL")
    private BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Boolean getEnabled() {

        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
