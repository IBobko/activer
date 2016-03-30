package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.FriendDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;

import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
public class GlobalListenerController {


    @Autowired
    private AccountDao accountService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private MessageDao messageDao;

    @MessageMapping("/actions")
    public void message(final ReceiveMessageData message, final Principal principal) {
//        switch (message.getPayload().getType()) {
//            case "private-message": /*todo something*/
//                break;
//            case "add-to-friend": /*todo something*/
//                break;
//            case "like-message": /*todo something*/
//                break;
//            case "wall-message": /*todo something*/
//                break;
//        }

        if (message.getType().equals("PRIVATE_MESSAGE")) {

            final PacketMessageData messageData = new PacketMessageData();


            final AccountItem from = accountService.get(principal.getName());
            messageData.setFrom(getMessageAccountData(from));
            final AccountItem to = accountService.get(message.getTo());
            messageData.setTo(getMessageAccountData(to));
            messageData.setDate(new GregorianCalendar());
            messageData.setMessage(message.getMessage());

            //AccountItem to = accountService.get()
            //messageData.setFrom(getMessageAccountData(from));


            //template.convertAndSend("/global2/1", messageData);

            //template.convertAndSend("/global2/1", messageData);

            template.convertAndSendToUser(principal.getName(), "/global2", messageData);

            // template.convertAndSend("/global2",messageData);

            MessageItem messageItem = new MessageItem();
            messageItem.setAccountFrom(from.getId());
            messageItem.setAccountTo(to.getId());
            messageItem.setAddedDate(new GregorianCalendar());
            messageItem.setText(message.getMessage());
            messageDao.save(messageItem);

        }

        if (message.getType().equals("ADD_TO_FRIEND")) {
            final AccountItem from = accountService.get(principal.getName());
            friendDao.addRequest(from.getId(),message.getTo());
        }

    }

    @MessageExceptionHandler

    public String handleException(Exception ex) {
        System.out.println(ex);
        return ex.getMessage();
    }


    private MessageAccountData getMessageAccountData(AccountItem accountItem){
        final MessageAccountData messageAccountData = new MessageAccountData();
        messageAccountData.setFirstName(accountItem.getFirstName());
        messageAccountData.setLastName(accountItem.getLastName());
        messageAccountData.setId(accountItem.getId());
        return messageAccountData;
    }


}
