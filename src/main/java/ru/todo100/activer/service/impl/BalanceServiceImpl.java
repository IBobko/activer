package ru.todo100.activer.service.impl;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.BalanceDao;
import ru.todo100.activer.dao.PaymentCreditDao;
import ru.todo100.activer.dao.PaymentDebitDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.model.PaymentCreditItem;
import ru.todo100.activer.model.PaymentDebitItem;
import ru.todo100.activer.service.BalanceService;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class BalanceServiceImpl implements BalanceService {
    private AccountDao accountService;
    private BalanceDao balanceDao;
    private PaymentDebitDao paymentDebitDao;
    private PaymentCreditDao paymentCreditDao;

    private PaymentDebitDao getPaymentDebitDao() {
        return paymentDebitDao;
    }

    @Autowired
    public void setPaymentDebitDao(PaymentDebitDao paymentDebitDao) {
        this.paymentDebitDao = paymentDebitDao;
    }

    private PaymentCreditDao getPaymentCreditDao() {
        return paymentCreditDao;
    }

    @Autowired
    public void setPaymentCreditDao(PaymentCreditDao paymentCreditDao) {
        this.paymentCreditDao = paymentCreditDao;
    }

    private BalanceDao getBalanceDao() {
        return balanceDao;
    }

    @Autowired
    public void setBalanceDao(BalanceDao balanceDao) {
        this.balanceDao = balanceDao;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    private void setAccountBalanceSum(final AccountItem accountItem, final BigDecimal sum) {
        final BalanceItem balanceItem = getBalanceDao().createOrGet(accountItem);
        balanceItem.setSum(sum);
        getBalanceDao().save(balanceItem);
        getAccountService().addSynchronizer(accountItem.getId(), "balance", sum);
    }

    public BigDecimal getSpentSumByAccount(Integer account_id) {
        final NativeQuery q = getBalanceDao().getSession().createNativeQuery("SELECT SUM(payment_debit_sum) FROM payment_debit ORDER BY id;");
        final Integer sum = (Integer) q.uniqueResult();
        return new BigDecimal(sum);
    }

    @Override
    public boolean subtractAccountBalanceSum(final Integer accountId, final BigDecimal subtrahend, final String description) {
        final AccountItem accountItem = getAccountService().get(accountId);
        final BalanceItem balanceItem = getBalanceDao().createOrGet(accountItem);
        int compare = balanceItem.getSum().compareTo(subtrahend);
        if (compare >= 0) {
            BigDecimal newSum = balanceItem.getSum().subtract(subtrahend);
            setAccountBalanceSum(accountItem, newSum);
            final PaymentCreditItem paymentCreditItem = new PaymentCreditItem();
            paymentCreditItem.setAccount(accountItem);
            paymentCreditItem.setPaymentCreditDescription(description);
            paymentCreditItem.setPaymentCreditSum(subtrahend);
            paymentCreditItem.setDate(new GregorianCalendar());
            getPaymentCreditDao().save(paymentCreditItem);

            final PaymentDebitItem paymentDebitItem = new PaymentDebitItem();
            paymentDebitItem.setAccountId(accountItem.getId());
            paymentDebitItem.setPaymentDebitDescription(description);
            paymentDebitItem.setPaymentDebitSum(subtrahend);
            paymentDebitItem.setDate(new GregorianCalendar());
            getPaymentDebitDao().save(paymentDebitItem);

            return true;
        }
        return false;
    }

    @Override
    public void additionAccountBalanceSum(final Integer accountId, final BigDecimal term, final String description) {
        final AccountItem accountItem = getAccountService().get(accountId);
        final BalanceItem balanceItem = getBalanceDao().createOrGet(accountItem);
        final BigDecimal newSum = balanceItem.getSum().add(term);

        /*Сделать корректное поступление средств*/
        setAccountBalanceSum(accountItem, newSum);
    }

}
