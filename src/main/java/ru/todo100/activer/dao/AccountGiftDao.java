package ru.todo100.activer.dao;

import org.hibernate.criterion.Restrictions;
import ru.todo100.activer.model.AccountGiftItem;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.GiftItem;

import javax.transaction.Transactional;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class AccountGiftDao extends AbstractDao {

    @Override
    public Class getItemClass() {
        return AccountGiftItem.class;
    }

    @Transactional
    public void give(Integer accountFromId, Integer accountId, Integer giftId,String message) {
        final AccountGiftItem accountGiftItem = new AccountGiftItem();
        accountGiftItem.setGivenDate(new GregorianCalendar());
        accountGiftItem.setMessage(message);
        final AccountItem from = new AccountItem();
        from.setId(accountFromId);
        accountGiftItem.setFrom(from);
        final AccountItem account = new AccountItem();
        account.setId(accountId);
        accountGiftItem.setAccount(account);
        final GiftItem gift = new GiftItem();
        gift.setId(giftId);
        accountGiftItem.setGift(gift);
        getSession().save(accountGiftItem);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<AccountGiftItem> getGiftsByAccount(final Integer id) {
        return getCriteria().add(Restrictions.eq("account.id",id)).list();
    }

}
