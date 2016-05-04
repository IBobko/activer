package ru.todo100.activer.data;

import java.util.Calendar;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class HappenedFlirtData {
    private Integer id;
    private Integer age;
    private Calendar startedDate;
    private String opponentAvatar;
    private String opponentFirstName;
    private String opponentLastName;
    private List<String> interests;
    private Calendar birthday;

    public int getAge() {
        Calendar dob = getBirthday();
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob.
                get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Calendar startedDate) {
        this.startedDate = startedDate;
    }

    public String getOpponentAvatar() {
        return opponentAvatar;
    }

    public void setOpponentAvatar(String opponentAvatar) {
        this.opponentAvatar = opponentAvatar;
    }

    public String getOpponentFirstName() {
        return opponentFirstName;
    }

    public void setOpponentFirstName(String opponentFirstName) {
        this.opponentFirstName = opponentFirstName;
    }

    public String getOpponentLastName() {
        return opponentLastName;
    }

    public void setOpponentLastName(String opponentLastName) {
        this.opponentLastName = opponentLastName;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

}
