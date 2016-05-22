package ru.todo100.activer.service;

import ru.todo100.activer.data.FriendsData1;

import javax.servlet.http.HttpSession;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface FriendsService {
    FriendsData1 getFriendData1(HttpSession session);
    void deleteFriend(Integer friendId);
    void synchronize(HttpSession session);
    void add(Integer friendId);
}
