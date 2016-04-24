package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.dao.PaymentDebitDao;
import ru.todo100.activer.model.PaymentDebitItem;
import ru.todo100.activer.service.PaymentService;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PaymentServiceImpl implements PaymentService{
    public PaymentDebitDao getPaymentDebitDao() {
        return paymentDebitDao;
    }

    public void setPaymentDebitDao(PaymentDebitDao paymentDebitDao) {
        this.paymentDebitDao = paymentDebitDao;
    }

    @Autowired
    private PaymentDebitDao paymentDebitDao;

    @Override
    @Transactional
    public void addPayment(Integer account_id, BigDecimal sum) {
        PaymentDebitItem item = new PaymentDebitItem();
        item.setAccountId(account_id);
        item.setPaymentDebitSum(sum);
        getPaymentDebitDao().getSession().save(item);
    }
}
