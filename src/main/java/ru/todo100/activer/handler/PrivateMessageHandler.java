package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.AccountGiftDao;
import ru.todo100.activer.dao.GiftDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.GiftItem;
import ru.todo100.activer.model.MessageItem;
import ru.todo100.activer.service.BalanceService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PrivateMessageHandler extends AbstractMessageHandler {

    @Autowired
    private MessageDao messageDao;
    private BalanceService balanceService;
    @Autowired
    private AccountDao accountService;
    @Autowired
    private GiftDao giftDao;
    @Value("${static.host.files}")
    private String staticHost;

    @Autowired
    private AccountGiftDao accountGiftDao;

    public BalanceService getBalanceService() {
        return balanceService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public void handle(ReceiveMessageData message, Principal principal) {

        final PacketMessageData messageData = new PacketMessageData();

        final AccountItem from = accountService.get(principal.getName());
        messageData.setFrom(generateAccountData(from));
        final AccountItem to = accountService.get(message.getTo());
        messageData.setTo(generateAccountData(to));
        messageData.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(new GregorianCalendar().getTime()));

        String messageText = message.getMessage();
        if (messageText.startsWith("gift:")) {
            final PacketMessageData spentMessage = new PacketMessageData();
            spentMessage.setType(PopupMessageType.SPENT);
            spentMessage.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(new GregorianCalendar().getTime()));
            Integer giftId = Integer.parseInt(messageText.substring(messageText.indexOf("gift:") + 5));
            final GiftItem gift = (GiftItem) giftDao.get(giftId);
            if (getBalanceService().subtractAccountBalanceSum(from.getId(), gift.getCost(), "Оплата подарка")) {
                messageText = "<img src='" + staticHost + "/" + gift.getFile() + ".'/>";
                spentMessage.setMessage(gift.getCost().toString());
                accountGiftDao.give(from.getId(),to.getId(),giftId,"Из сообщений");
            } else {
                spentMessage.setMessage("0");
            }
            getTemplate().convertAndSendToUser(principal.getName(), "/global2", spentMessage);
            if (spentMessage.getMessage().equals("0")) {
                return;
            }
        }

        messageData.setMessage(messageText);
        messageData.setType(PopupMessageType.PRIVATE_MESSAGE);

        getTemplate().convertAndSendToUser(principal.getName(), "/global2", messageData);
        getTemplate().convertAndSendToUser(to.getEmail(), "/global2", messageData);

        MessageItem messageItem = new MessageItem();
        messageItem.setAccountFrom(from.getId());
        messageItem.setAccountTo(to.getId());
        messageItem.setAddedDate(new GregorianCalendar());
        messageItem.setText(messageText);
        messageDao.save(messageItem);

    }
}
