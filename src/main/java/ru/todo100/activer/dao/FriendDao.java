package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.data.FriendData;
import ru.todo100.activer.model.AccountFriendRelationItem;
import ru.todo100.activer.model.AccountItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class FriendDao extends AbstractDao  {
    @Override
    public Class getItemClass() {
        return AccountFriendRelationItem.class;
    }

    public FriendData getFriends(Integer accountId) {
        List<AccountFriendRelationItem> items1 =
                getCriteria().add(Restrictions.eq("account.id",accountId)).list();

        List<AccountFriendRelationItem> items2 =
                getCriteria().add(Restrictions.eq("friendAccount.id",accountId)).list();

        List<AccountItem> friends = new ArrayList<>();
        List<AccountItem> outRequest = new ArrayList<>();
        List<AccountItem> inRequest = new ArrayList<>();

        HashSet<AccountFriendRelationItem> deletedIn = new HashSet<>();
        HashSet<AccountFriendRelationItem> deletedOut = new HashSet<>();

        for (AccountFriendRelationItem item: items1) {
            for (AccountFriendRelationItem item2: items2) {
                if (item.getFriendAccount().getId().equals(item2.getAccount().getId())) {
                    friends.add(item.getFriendAccount());
                    deletedIn.add(item);
                    deletedOut.add(item2);
                    break;
                }
            }
        }

        for (AccountFriendRelationItem item: items1) {
            if (!deletedIn.contains(item)) {
                inRequest.add(item.getFriendAccount());
            }
        }

        for (AccountFriendRelationItem item: items2) {
            if (!deletedOut.contains(item)) {
                outRequest.add(item.getAccount());
            }
        }

        final FriendData friendData = new FriendData();
        friendData.setFriends(friends);
        friendData.setOutRequest(outRequest);
        friendData.setInRequest(inRequest);
        return friendData;
    }

    public void addRequest(Integer accountId, Integer FriendId) {
        AccountFriendRelationItem item = new AccountFriendRelationItem();
        AccountItem item1 = getSession().load(AccountItem.class,accountId);
        item.setAccount(item1);

        AccountItem item2 = getSession().load(AccountItem.class,FriendId);
        item.setFriendAccount(item2);

        getSession().save(item);

    }


}

