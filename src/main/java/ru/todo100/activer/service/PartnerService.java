package ru.todo100.activer.service;

import ru.todo100.activer.model.AccountItem;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface PartnerService {
    List<AccountItem> getPartners(Integer accountId);
}
