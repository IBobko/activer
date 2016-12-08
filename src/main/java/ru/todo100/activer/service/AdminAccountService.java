package ru.todo100.activer.service;

import ru.todo100.activer.data.AdminAccountData;
import ru.todo100.activer.data.AdminAccountQualifier;

import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public interface AdminAccountService {
    void synchronize();
    Long getAccountsCount(AdminAccountQualifier qualifier);
    List<AdminAccountData> getAccounts(AdminAccountQualifier qualifier);
    Long getTotalAccountAmount();
    Long getTotalOnlineAccountAmount();
}
