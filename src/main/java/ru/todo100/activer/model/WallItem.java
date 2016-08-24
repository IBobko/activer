package ru.todo100.activer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

/**
 * @author Igor Bobko
 */
@Entity
@Table(name = "wall")
public class WallItem extends Item {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "wall_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountItem account;
    @NotNull
    @Column(name = "text", nullable = false)
    private String text;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date", nullable = false)
    private Calendar addedDate;
    @NotNull
    @Column(name = "sender_id", nullable = false)
    private Integer sender;

    @OneToMany(mappedBy = "wall",cascade = CascadeType.ALL)
    private Set<WallAttachmentItem> attachments;

    @SuppressWarnings("unused")
    @OneToMany(mappedBy = "wall",orphanRemoval = true)
    /* It is needed inly for deleting. When wall item going to be deleted then news too.*/
    private Set<WallNewsItem> news;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<WallAttachmentItem> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<WallAttachmentItem> attachments) {
        this.attachments = attachments;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(final Integer sender) {
        this.sender = sender;
    }

    public Calendar getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(final Calendar addedDate) {
        this.addedDate = addedDate;
    }

    public AccountItem getAccount() {
        return account;
    }

    public void setAccount(final AccountItem account) {
        this.account = account;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

}
