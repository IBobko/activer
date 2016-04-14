package ru.todo100.activer.service.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.service.PartnerService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Transactional
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private AccountDao accountService;

    @SuppressWarnings("unchecked")
    @Override
    public List<AccountItem> getPartners(Integer accountId) {
        AccountItem accountItem = accountService.get(accountId);
        return accountService.getCriteria().add(Restrictions.eq("usedReferCode", accountItem.getReferCode())).list();
    }
}
