package ru.todo100.activer.service;

import ru.todo100.activer.controller.BalanceQualifier;
import ru.todo100.activer.data.BalanceData;
import ru.todo100.activer.data.PartnerData;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PaymentService {
    void addPayment(Integer account_id, BigDecimal sum);

    Long getBalanceCount(BalanceQualifier qualifier);

    List<BalanceData> getBalances(BalanceQualifier qualifier);
}

