package ru.todo100.activer.dao;

import ru.todo100.activer.model.PayPalAccountItem;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PayPalAccountDao extends AbstractDao {

    @Override
    public Class getItemClass() {
        return PayPalAccountItem.class;
    }

}
