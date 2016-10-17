package ru.todo100.activer.service;

import ru.todo100.activer.data.FriendsData;

import javax.servlet.http.HttpSession;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface FriendsService {
    FriendsData getFriendData(HttpSession session);
    void deleteFriend(Integer friendId);
    void synchronize(HttpSession session);
    void add(Integer friendId);
}
