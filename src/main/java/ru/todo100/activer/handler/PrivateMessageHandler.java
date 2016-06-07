package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.BalanceDao;
import ru.todo100.activer.dao.GiftDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.BalanceItem;
import ru.todo100.activer.model.GiftItem;
import ru.todo100.activer.model.MessageItem;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class PrivateMessageHandler extends AbstractMessageHandler {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private AccountDao accountService;

    @Autowired
    private GiftDao giftDao;

    @Value("${static.host.files}")
    private String staticHost;

    @Autowired
    private BalanceDao balanceDao;


    @Override
    public void handle(ReceiveMessageData message, Principal principal) {

        final PacketMessageData messageData = new PacketMessageData();

        final AccountItem from = accountService.get(principal.getName());
        messageData.setFrom(generateAccountData(from));
        final AccountItem to = accountService.get(message.getTo());
        messageData.setTo(generateAccountData(to));
        messageData.setDate(new GregorianCalendar());

        String messageText = message.getMessage();
        if (messageText.startsWith("gift:")) {

            final PacketMessageData spentMessage = new PacketMessageData();
            spentMessage.setType(PopupMessageType.SPENT);
            spentMessage.setDate(new GregorianCalendar());

            BigDecimal costOfGift = new BigDecimal("1");

            BalanceItem balanceItem = balanceDao.createOrGet(from);
            int compare = balanceItem.getSum().compareTo(costOfGift);
            if (compare == 1 || compare == 0) {
                BigDecimal newSum = balanceItem.getSum().subtract(costOfGift);
                balanceItem.setSum(newSum);
                balanceDao.save(balanceItem);
                accountService.addSynchronizer(from.getId(),"balance",newSum);
                Integer giftId = Integer.parseInt(messageText.substring(messageText.indexOf("gift:") + 5));
                GiftItem gift = (GiftItem)giftDao.get(giftId);
                messageText = "<img src='"+staticHost+"/" + gift.getFile() + ".'/>";
                spentMessage.setMessage(costOfGift.toString());
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

        //AccountItem to = accountService.get()
        //messageData.setFrom(getMessageAccountData(from));


        //template.convertAndSend("/global2/1", messageData);

        //template.convertAndSend("/global2/1", messageData);

        getTemplate().convertAndSendToUser(principal.getName(), "/global2", messageData);
        getTemplate().convertAndSendToUser(to.getEmail(), "/global2", messageData);


        // template.convertAndSend("/global2",messageData);

        MessageItem messageItem = new MessageItem();
        messageItem.setAccountFrom(from.getId());
        messageItem.setAccountTo(to.getId());
        messageItem.setAddedDate(new GregorianCalendar());
        messageItem.setText(messageText);
        messageDao.save(messageItem);

    }
}
