package ru.todo100.activer.service;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PaymentService {
    void addPayment(Integer account_id, BigDecimal sum);
}

