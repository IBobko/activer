package ru.todo100.activer.data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FriendsData implements Serializable {
    private List<FriendData> friends;
    private List<FriendData> outRequest;
    private List<FriendData> inRequest;

    public List<FriendData> getInRequest() {
        return inRequest;
    }

    public void setInRequest(List<FriendData> inRequest) {
        this.inRequest = inRequest;
    }

    public List<FriendData> getOutRequest() {
        return outRequest;
    }

    public void setOutRequest(List<FriendData> outRequest) {
        this.outRequest = outRequest;
    }

    public List<FriendData> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendData> friends) {
        this.friends = friends;
    }
}
