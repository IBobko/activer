package ru.todo100.activer.service;

import java.math.BigDecimal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface BalanceService {
    boolean subtractAccountBalanceSum(final Integer accountId, final BigDecimal subtrahend, String description);
    BigDecimal getSpentSumByAccount(final Integer account_id);
    void additionAccountBalanceSum(final Integer accountId, final BigDecimal term, final String description);
}
