package ru.todo100.activer.dao;

import ru.todo100.activer.model.PaymentItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PaymentDao extends AbstractDao{
    @Override
    public Class getItemClass() {
        return PaymentItem.class;
    }
}
