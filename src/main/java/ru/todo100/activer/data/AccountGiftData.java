package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AccountGiftData {
    private Integer id;
    private String senderFirstName;
    private String senderLastName;
    private String givenDate;
    private String fileName;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(String givenDate) {
        this.givenDate = givenDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
