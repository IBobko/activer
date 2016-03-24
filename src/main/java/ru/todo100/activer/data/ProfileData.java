package ru.todo100.activer.data;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class ProfileData {
    private Integer id;
    private String firstName;
    private String lastName;
    private String facePhotoUrl;
    private String birthDate;
    private String photo60x60;
    private List<ProfileData> friends;
    private EducationData education;
    private JobData job;
    private ChildrenData children;
    private Integer maritalStatus;
    private List<InterestData> interests;
    private List<TripData> trips;

    public List<TripData> getTrips() {
        return trips;
    }

    public void setTrips(List<TripData> trips) {
        this.trips = trips;
    }

    public List<InterestData> getInterests() {
        return interests;
    }

    public void setInterests(List<InterestData> interests) {
        this.interests = interests;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public JobData getJob() {
        return job;
    }

    public void setJob(JobData job) {
        this.job = job;
    }

    public ChildrenData getChildren() {
        return children;
    }

    public void setChildren(ChildrenData children) {
        this.children = children;
    }

    public EducationData getEducation() {
        return education;
    }

    public void setEducation(EducationData education) {
        this.education = education;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoto60x60() {
        return photo60x60;
    }

    public void setPhoto60x60(final String photo60x60) {
        this.photo60x60 = photo60x60;
    }

    public List<ProfileData> getFriends() {
        return friends;
    }

    public void setFriends(final List<ProfileData> friends) {
        this.friends = friends;
    }

    public String getFacePhotoUrl() {
        return facePhotoUrl;
    }

    public void setFacePhotoUrl(final String facePhotoUrl) {
        this.facePhotoUrl = facePhotoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
}
