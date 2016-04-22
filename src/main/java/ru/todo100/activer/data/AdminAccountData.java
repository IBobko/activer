package ru.todo100.activer.data;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AdminAccountData {
    private String firstName;
    private String lastName;
    private String type;
    private String networkCount;
    private String inviterName;
    private String inviterId;
    private String onoffline;
    private String email;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(String networkCount) {
        this.networkCount = networkCount;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public String getOnoffline() {
        return onoffline;
    }

    public void setOnoffline(String onoffline) {
        this.onoffline = onoffline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
