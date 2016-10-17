package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Entity
@Table(name = "happened_flirt")
public class HappenedFlirtItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "happened_flirt_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @NotNull
    @Column(name = "account_init", nullable = false)
    private Integer accountInitId;

    @NotNull
    @Column(name = "account_applied", nullable = false)
    private Integer accountAppliedId;

    @NotNull
    @Column(name = "started_date", nullable = false)
    private Calendar startedDate;

    @Column(name = "agree_init")
    private Integer agreeInit;

    @Column(name = "agree_applied")
    private Integer agreeApplied;

    public Integer getAgreeApplied() {
        return agreeApplied;
    }

    public void setAgreeApplied(Integer agreeApplied) {
        this.agreeApplied = agreeApplied;
    }

    public Integer getAgreeInit() {
        return agreeInit;
    }

    public void setAgreeInit(Integer agreeInit) {
        this.agreeInit = agreeInit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Calendar getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Calendar startedDate) {
        this.startedDate = startedDate;
    }
}
