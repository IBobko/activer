package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.FriendDao;
import ru.todo100.activer.data.FriendsData;
import ru.todo100.activer.data.FriendsData1;
import ru.todo100.activer.service.FriendsService;

import javax.servlet.http.HttpSession;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class FriendsServiceImpl implements FriendsService {

    private final String FRIENDS_DATA = "friendsData";
    private final String FRIENDS_DATA1 = "friendsData1";

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private AccountDao accountService;

    @Override
    public FriendsData getFriendData(HttpSession session) {
        FriendsData friendsData = (FriendsData)session.getAttribute(FRIENDS_DATA);
        if (friendsData == null) {
            final Integer currentID = accountService.getCurrentAccount().getId();
            friendsData = friendDao.getFriends(currentID);
            session.setAttribute(FRIENDS_DATA,friendsData);
        }
        return friendsData;
    }

    @Override
    public FriendsData1 getFriendData1(HttpSession session) {
        FriendsData1 friendsData = (FriendsData1) session.getAttribute(FRIENDS_DATA1);
        if (friendsData == null) {
            final Integer currentID = accountService.getCurrentAccount().getId();
            friendsData = friendDao.getFriends1(currentID);
            session.setAttribute(FRIENDS_DATA1, friendsData);
        }
        return friendsData;
    }

    @Override
    public void deleteFriend(Integer friendId) {
        final Integer currentID = accountService.getCurrentAccount().getId();
        friendDao.delete(currentID,friendId);
    }

    @Override
    public void synchronize(HttpSession session) {
        final Integer currentID = accountService.getCurrentAccount().getId();
        final FriendsData friendsData = friendDao.getFriends(currentID);
        session.setAttribute(FRIENDS_DATA,friendsData);
    }

    @Override
    public void add(Integer friendId) {
        final Integer currentID = accountService.getCurrentAccount().getId();
        friendDao.addRequest(currentID,friendId);
    }
}
