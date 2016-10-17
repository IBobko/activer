package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "happened_dispute")
public class HappenedDisputeItem {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "happened_dispute_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @NotNull
    @Column(name = "dispute_theme_id", nullable = false)
    private Integer themeId;

    @NotNull
    @Column(name = "account_init", nullable = false)
    private Integer accountInitId;

    @NotNull
    @Column(name = "account_applied", nullable = false)
    private Integer accountAppliedId;
    @NotNull
    @Column(name = "init_position", nullable = false)
    private Integer initPosition;
    @NotNull
    @Column(name = "applied_position", nullable = false)
    private Integer appliedPosition;
    @NotNull
    @Column(name = "started_dispute", nullable = false)
    private Calendar startedDispute;

    public Calendar getStartedDispute() {
        return startedDispute;
    }

    public void setStartedDispute(Calendar startedDispute) {
        this.startedDispute = startedDispute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getAccountInitId() {
        return accountInitId;
    }

    public void setAccountInitId(Integer accountInitId) {
        this.accountInitId = accountInitId;
    }

    public Integer getAccountAppliedId() {
        return accountAppliedId;
    }

    public void setAccountAppliedId(Integer accountAppliedId) {
        this.accountAppliedId = accountAppliedId;
    }

    public Integer getInitPosition() {
        return initPosition;
    }

    public void setInitPosition(Integer initPosition) {
        this.initPosition = initPosition;
    }

    public Integer getAppliedPosition() {
        return appliedPosition;
    }

    public void setAppliedPosition(Integer appliedPosition) {
        this.appliedPosition = appliedPosition;
    }
}
