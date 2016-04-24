package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.PaymentDao;
import ru.todo100.activer.service.PaymentService;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;
}
