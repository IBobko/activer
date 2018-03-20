package ru.todo100.activer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.NotificationDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.NotificationItem;
import ru.todo100.activer.service.NotificationService;
import ru.todo100.activer.service.PhotoService;

import java.util.GregorianCalendar;

public class NotificationServiceImpl implements NotificationService {
    private SimpMessagingTemplate template;
    private NotificationDao notificationDao;
    private AccountDao accountService;
    private PhotoService photoService;

    protected SimpMessagingTemplate getTemplate() {
        return template;
    }

    @Autowired
    protected void setTemplate(final SimpMessagingTemplate template) {
        this.template = template;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    @Autowired
    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public PhotoService getPhotoService() {
        return photoService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public void addNotification(final AccountItem fromAccount, final AccountItem toAccount, final Integer fromGroup, final Integer toGroup, final Integer type, final String message) {
        final NotificationItem notification = new NotificationItem();
        notification.setFromAccount(fromAccount);
        notification.setFromGroupId(fromGroup);
        notification.setToAccount(toAccount);
        notification.setToGroupId(toGroup);
        notification.setType(type);
        notification.setMessage(message);
        notification.setReadByUser(false);
        notification.setCreationDate(new GregorianCalendar());
        getNotificationDao().save(notification);

        if (getAccountService().isOnline(toAccount)) {
            final PacketMessageData messageData = new PacketMessageData();
            messageData.setType(PopupMessageType.FRIEND_REQUEST);
            final MessageAccountData from = new MessageAccountData();
            from.setFirstName(fromAccount.getFirstName());
            from.setLastName(fromAccount.getLastName());
            from.setId(fromAccount.getId());
            from.setOnline(fromAccount.isOnline());
            from.setPhoto60x60(getPhotoService().getSizedPhoto(toAccount.getId()).getPhotoMini());
            messageData.setFrom(from);
            getTemplate().convertAndSendToUser(fromAccount.getEmail(), "/global2", messageData);
        }
    }

    @Override
    public void addNotification(final Integer fromAccountId, final Integer toAccountId, final Integer fromGroup, final Integer toGroup, final Integer type, final String message) {
        final AccountItem fromAccount = getAccountService().get(fromAccountId);
        final AccountItem toAccount = getAccountService().get(toAccountId);
        addNotification(fromAccount, toAccount, fromGroup, toGroup, type, message);
    }

    @Override
    public void setRead(Integer notificationId) {
        final NotificationItem notification = (NotificationItem) getNotificationDao().get(notificationId);
        notification.setReadByUser(true);
        getNotificationDao().save(notification);
    }
}
