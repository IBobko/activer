package ru.todo100.activer.data;

import ru.todo100.activer.model.AccountItem;

import java.io.Serializable;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FriendsData implements Serializable{
    private List<AccountItem> friends;
    private List<AccountItem> outRequest;
    private List<AccountItem> inRequest;

    public List<AccountItem> getInRequest() {
        return inRequest;
    }

    public void setInRequest(List<AccountItem> inRequest) {
        this.inRequest = inRequest;
    }

    public List<AccountItem> getOutRequest() {
        return outRequest;
    }

    public void setOutRequest(List<AccountItem> outRequest) {
        this.outRequest = outRequest;
    }

    public List<AccountItem> getFriends() {
        return friends;
    }

    public void setFriends(List<AccountItem> friends) {
        this.friends = friends;
    }
}
