package ru.todo100.activer.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"JpaDataSourceORMInspection", "unused"})
@Entity
@Table(name = "account")
@DynamicUpdate
@FetchProfile(name = "account-for-profile", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "educationItems", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "childrenItems", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "interestItems", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "dreamItems", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "tripItems", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(entity = AccountItem.class, association = "jobItems", mode = FetchMode.JOIN)
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccountItem extends DateChanges implements Serializable {
    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Integer id;

    @OneToMany
    @JoinColumn(name = "account_username", referencedColumnName = "account_username")
    private Set<AuthorityItem> authorities;

    @Column(name = "account_refer_code", nullable = false)
    private String referCode;

    @Column(name = "account_used_refer_code", nullable = false)
    private String usedReferCode;

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

    @Column(name = "account_sex")
    private Integer sex;

    @Column(name = "account_birthdate")
    private Calendar birthdate;

    @Column(name = "account_maritalstatus")
    private Integer maritalStatus;

    /*Благодаря CascadeType.ALL удается сохранять айтемы, однако я не уверен что это обязательно. нужно проверить */
    /*Использование Set вместо List крайне важно, так как пораждает возможносьти множественного FETCH.JOIN*/
    /*По умолчанию используется ленивая загрузка однако, однако мы это исправим в профайлах*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<EducationItem> educationItems;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<JobItem> jobItems;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<ChildrenItem> childrenItems;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<InterestItem> interestItems;

    /*
    orphanRemoval = true
    It means that if you delete items from collection that item will be deleted from database.
    */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<DreamItem> dreamItems;

    /*
    orphanRemoval = true
    It means that if you delete items from collection that item will be deleted from database.
    */
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Set<TripItem> tripItems;

    @Column(name = "account_last_activity")
    private Calendar lastActivity;

    public Calendar getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Calendar lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Set<DreamItem> getDreamItems() {
        return dreamItems;
    }

    public void setDreamItems(Set<DreamItem> dreamItems) {
        this.dreamItems = dreamItems;
    }

    public Set<TripItem> getTripItems() {
        return tripItems;
    }

    public void setTripItems(Set<TripItem> tripItems) {
        this.tripItems = tripItems;
    }

    public Set<InterestItem> getInterestItems() {
        return interestItems;
    }

    public void setInterestItems(Set<InterestItem> interestItems) {
        this.interestItems = interestItems;
    }

    public Set<EducationItem> getEducationItems() {
        return educationItems;
    }

    public void setEducationItems(Set<EducationItem> educationItems) {
        this.educationItems = educationItems;
    }

    public Set<JobItem> getJobItems() {
        return jobItems;
    }

    public void setJobItems(Set<JobItem> jobItems) {
        this.jobItems = jobItems;
    }

    public Set<ChildrenItem> getChildrenItems() {
        return childrenItems;
    }

    public void setChildrenItems(Set<ChildrenItem> childrenItems) {
        this.childrenItems = childrenItems;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

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
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        AuthorityItem item = new AuthorityItem();
        item.setRole(role);
        item.setAccount(this);
        authorities.add(item);
    }

    public Set<AuthorityItem> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final Set<AuthorityItem> authorities) {
        this.authorities = authorities;
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

    public boolean getIsOnline() {
        final Calendar calendar = getLastActivity();
        return calendar != null && new GregorianCalendar().getTimeInMillis() - calendar.getTimeInMillis() <= 300000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountItem that = (AccountItem) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
