package ru.todo100.activer.dao;

import ru.todo100.activer.model.PaymentCreditItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PaymentCreditDao extends AbstractDao {
    @Override
    public Class getItemClass() {
        return PaymentCreditItem.class;
    }
}
