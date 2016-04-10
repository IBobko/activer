package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.data.FriendsData;
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


    public FriendsData getFriends(Integer accountId) {
        @SuppressWarnings("unchecked")
        final List<AccountFriendRelationItem> outRelations = getCriteria().add(Restrictions.eq("account.id",accountId)).list();

        @SuppressWarnings("unchecked")
        final List<AccountFriendRelationItem> inRelations = getCriteria().add(Restrictions.eq("friendAccount.id",accountId)).list();


        final List<AccountItem> friends = new ArrayList<>();
        final List<AccountItem> outRequest = new ArrayList<>();
        final List<AccountItem> inRequest = new ArrayList<>();

        HashSet<AccountFriendRelationItem> deletedIn = new HashSet<>();
        HashSet<AccountFriendRelationItem> deletedOut = new HashSet<>();

        for (final AccountFriendRelationItem outRelation: outRelations) {
            boolean find = false;

            for (final AccountFriendRelationItem inRelation: inRelations) {
                if (outRelation.getFriendAccount().getId().equals(inRelation.getAccount().getId())) {
                    friends.add(outRelation.getFriendAccount());
                    find = true;
                    deletedIn.add(outRelation);
                    deletedOut.add(inRelation);
                    break;
                }
            }
        }

        for (AccountFriendRelationItem item: outRelations) {
            if (!deletedIn.contains(item)) {
                inRequest.add(item.getFriendAccount());
            }
        }

        for (AccountFriendRelationItem item: inRelations) {
            if (!deletedOut.contains(item)) {
                outRequest.add(item.getAccount());
            }
        }

        final FriendsData friendsData = new FriendsData();
        friendsData.setFriends(friends);
        friendsData.setOutRequest(outRequest);
        friendsData.setInRequest(inRequest);
        return friendsData;
    }

    public void addRequest(Integer accountId, Integer FriendId) {
        AccountFriendRelationItem item = new AccountFriendRelationItem();
        AccountItem item1 = getSession().load(AccountItem.class,accountId);
        item.setAccount(item1);

        AccountItem item2 = getSession().load(AccountItem.class,FriendId);
        item.setFriendAccount(item2);

        getSession().save(item);

    }

    public void delete(Integer accountId, Integer friendId) {
        final AccountFriendRelationItem friend = (AccountFriendRelationItem) getCriteria()
                .add(Restrictions.and(Restrictions.eq("account.id", accountId),Restrictions.eq("friendAccount.id", friendId)))
                .uniqueResult();
        getSession().delete(friend);
    }


}

