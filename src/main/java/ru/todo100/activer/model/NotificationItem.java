package ru.todo100.activer.model;

import javax.persistence.*;
import java.util.Calendar;
@Entity
@Table(name = "notification")
public class NotificationItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "NOTIFICATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id")
    private AccountItem fromAccount;
    @Column(name = "from_group_id")
    private Integer fromGroupId;
    @Column(name = "type")
    private Integer type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    private AccountItem toAccount;
    @Column(name = "to_group_id")
    private Integer toGroupId;
    @Column(name = "message")
    private String message;
    @Column(name = "creation_date")
    private Calendar creationDate;
    @Column(name = "modification_date")
    private Calendar modificationDate;
    @Column(name = "read_by_user")
    private Boolean readByUser;

    public Integer getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(Integer fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Boolean getReadByUser() {
        return readByUser;
    }

    public void setReadByUser(Boolean readByUser) {
        this.readByUser = readByUser;
    }

    public AccountItem getToAccount() {
        return toAccount;
    }

    public void setToAccount(AccountItem toAccount) {
        this.toAccount = toAccount;
    }

    public AccountItem getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AccountItem fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
