package ru.todo100.activer.dao;

import ru.todo100.activer.model.PaymentDebitItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PaymentDebitDao extends AbstractDao{
    @Override
    public Class getItemClass() {
        return PaymentDebitItem.class;
    }
}
