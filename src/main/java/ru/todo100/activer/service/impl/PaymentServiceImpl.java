package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.todo100.activer.controller.BalanceQualifier;
import ru.todo100.activer.dao.PaymentCreditDao;
import ru.todo100.activer.dao.PaymentDebitDao;
import ru.todo100.activer.data.BalanceData;
import ru.todo100.activer.data.PartnerData;
import ru.todo100.activer.model.PaymentCreditItem;
import ru.todo100.activer.model.PaymentDebitItem;
import ru.todo100.activer.service.PaymentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentCreditDao paymentCreditDao;

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

    @Override
    public Long getBalanceCount(BalanceQualifier qualifier) {
        return 100L;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BalanceData> getBalances(BalanceQualifier qualifier) {
        final List<BalanceData> result = new ArrayList<>();

        final List<PaymentCreditItem> payments = paymentCreditDao.getCriteria().list();

        for (PaymentCreditItem p: payments) {
            BalanceData balanceData = new BalanceData();
            balanceData.setAccountName(p.getAccount().getLastName() + " " + p.getAccount().getFirstName());
            balanceData.setDescription(p.getPaymentCreditDescription());
            balanceData.setSum(p.getPaymentCreditSum());
            result.add(balanceData);
        }

        return result;
    }
}
