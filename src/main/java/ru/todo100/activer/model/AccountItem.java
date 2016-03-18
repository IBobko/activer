package ru.todo100.activer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "account")

public class AccountItem extends DateChanges {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @Column(name = "account_refer_code", nullable = false)
    private String referCode;

    @Column(name = "account_used_refer_code", nullable = false)
    private String usedReferCode;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_username", referencedColumnName = "account_username")
    private List<AuthorityItem> authoritys;
    @NaturalId
    @NotNull
    @Column(name = "account_username", nullable = false)
    private String username;
    @NotNull
    @Column(name = "account_password", nullable = false)
    private String password;
    @NotNull
    @Column(name = "account_email", nullable = false)
    private String email;
    @NotNull
    @Column(name = "account_firstname", nullable = false)
    private String firstName;
    @NotNull
    @Column(name = "account_lastname", nullable = false)
    private String lastName;
    @ManyToMany
    @JoinTable(name = "account_friend_relation",
            joinColumns = @JoinColumn(name = "account_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "friend_account_id", nullable = false)
    )
    private Set<AccountItem> friends;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsedReferCode() {
        return usedReferCode;
    }

    public void setUsedReferCode(String usedReferCode) {
        this.usedReferCode = usedReferCode;
    }

    public Set<AccountItem> getFriends() {
        return friends;
    }

    public void setFriends(final Set<AccountItem> friends) {
        this.friends = friends;
    }

    @SuppressWarnings("unchecked")
    public void addRole(String role) {
        if (authoritys == null) {
            authoritys = new ArrayList();
        }
        AuthorityItem item = new AuthorityItem();
        item.setRole(role);
        item.setAccount(this);
        authoritys.add(item);
    }

    public List<AuthorityItem> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(final List<AuthorityItem> authoritys) {
        this.authoritys = authoritys;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "[(" + getEmail() + ") " + getFirstName() + " " + getLastName() + "]";
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

}
