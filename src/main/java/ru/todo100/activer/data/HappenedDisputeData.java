package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class HappenedDisputeData {
    private Integer id;
    private String themeTitle;
    private String opponentPosition;
    private String yourPosition;
    private String startedDate;
    private String opponentFistName;
    private Integer opponentId;
    private String opponentLastName;
    private String opponentAvatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getOpponentPosition() {
        return opponentPosition;
    }

    public void setOpponentPosition(String opponentPosition) {
        this.opponentPosition = opponentPosition;
    }

    public String getYourPosition() {
        return yourPosition;
    }

    public void setYourPosition(String yourPosition) {
        this.yourPosition = yourPosition;
    }

    public String getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(String startedDate) {
        this.startedDate = startedDate;
    }

    public String getOpponentFistName() {
        return opponentFistName;
    }

    public void setOpponentFistName(String opponentFistName) {
        this.opponentFistName = opponentFistName;
    }

    public Integer getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(Integer opponentId) {
        this.opponentId = opponentId;
    }

    public String getOpponentLastName() {
        return opponentLastName;
    }

    public void setOpponentLastName(String opponentLastName) {
        this.opponentLastName = opponentLastName;
    }

    public String getOpponentAvatar() {
        return opponentAvatar;
    }

    public void setOpponentAvatar(String opponentAvatar) {
        this.opponentAvatar = opponentAvatar;
    }
}
