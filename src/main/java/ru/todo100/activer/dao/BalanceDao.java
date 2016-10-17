package ru.todo100.activer.dao;

import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.BalanceItem;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class BalanceDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return BalanceItem.class;
    }
    @Transactional
    public BalanceItem createOrGet(AccountItem accountItem) {
        BalanceItem balance = (BalanceItem)get(accountItem.getId());
        if (balance == null) {
            balance = new BalanceItem();
            balance.setSum(new BigDecimal("0"));
            balance.setId(accountItem.getId());
            save(balance);
        }
        return balance;
    }

}
