package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;

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

    @Override
    public void handle(ReceiveMessageData message, Principal principal) {

        final PacketMessageData messageData = new PacketMessageData();


        final AccountItem from = accountService.get(principal.getName());
        messageData.setFrom(generateAccountData(from));
        final AccountItem to = accountService.get(message.getTo());
        messageData.setTo(generateAccountData(to));
        messageData.setDate(new GregorianCalendar());
        messageData.setMessage(message.getMessage());
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
        messageItem.setText(message.getMessage());
        messageDao.save(messageItem);

    }
}
