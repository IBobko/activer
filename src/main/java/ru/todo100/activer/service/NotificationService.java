package ru.todo100.activer.service;

import ru.todo100.activer.model.AccountItem;

public interface NotificationService {
    void addNotification(final AccountItem fromAccount, final AccountItem toAccount, final Integer fromGroup, final Integer toGroup, final Integer type, final String message);
    void addNotification(final Integer fromAccount, final Integer toAccount, final Integer fromGroup, final Integer toGroup, final Integer type, final String message);
    void setRead(final Integer notificationId);
}
