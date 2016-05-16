package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FriendData {
    public Integer id;
    public String firstName;
    public String lastName;
    public String photo60x60;
    public JobData job;

    public JobData getJob() {
        return job;
    }

    public void setJob(JobData job) {
        this.job = job;
    }

    public String getPhoto60x60() {
        return photo60x60;
    }

    public void setPhoto60x60(String photo60x60) {
        this.photo60x60 = photo60x60;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
