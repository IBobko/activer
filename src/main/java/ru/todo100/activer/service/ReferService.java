package ru.todo100.activer.service;

import ru.todo100.activer.model.AccountItem;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface ReferService {
    AccountItem getUserByRefer(String referCode);
    Long getCountOfUsedReferCode(String referCode);
    List<AccountItem> getByReferCode(String referCode);
}
