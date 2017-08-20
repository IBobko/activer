package ru.todo100.activer.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.handler.DisputeHandler;
import ru.todo100.activer.handler.FlirtHandler;
import ru.todo100.activer.handler.PrivateMessageHandler;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AccountPhotoItem;
import ru.todo100.activer.model.AccountPhotoLikeItem;

import javax.transaction.Transactional;
import java.security.Principal;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("StatementWithEmptyBody")
@Controller
public class GlobalListenerController {
    private PrivateMessageHandler privateMessageHandler;
    private DisputeHandler disputeHandler;
    private FlirtHandler flirtHandler;

    public PrivateMessageHandler getPrivateMessageHandler() {
        return privateMessageHandler;
    }

    @Autowired
    public void setPrivateMessageHandler(PrivateMessageHandler privateMessageHandler) {
        this.privateMessageHandler = privateMessageHandler;
    }

    public DisputeHandler getDisputeHandler() {
        return disputeHandler;
    }

    @Autowired
    public void setDisputeHandler(DisputeHandler disputeHandler) {
        this.disputeHandler = disputeHandler;
    }

    public FlirtHandler getFlirtHandler() {
        return flirtHandler;
    }

    @Autowired
    public void setFlirtHandler(FlirtHandler flirtHandler) {
        this.flirtHandler = flirtHandler;
    }

    @MessageMapping("/actions")
    public void message(final ReceiveMessageData message, final Principal principal) {
        if (message.getType().equals("DISPUTE_MESSAGE")) {
            getDisputeHandler().handle(message, principal);
            return;
        }
        if (message.getType().equals("FLIRT_MESSAGE")) {
            getFlirtHandler().handle(message, principal);
            return;
        }

        if (message.getType().equals("PRIVATE_MESSAGE")) {
            getPrivateMessageHandler().handle(message, principal);
            return;
        }

        if (message.getType().equals("ADD_TO_FRIEND")) {
            //final AccountItem from = accountService.get(principal.getName());
            //friendDao.addRequest(from.getId(),message.getTo());
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @MessageMapping("/likes")
    @Transactional
    public void likes(final Integer photo_id, final Principal principal) {
        AccountItem accountItem = getSessionFactory().getCurrentSession().byNaturalId(AccountItem.class).using("username",principal.getName()).load();
        AccountPhotoItem accountPhotoItem = getSessionFactory().getCurrentSession().byId(AccountPhotoItem.class).load(photo_id);
        AccountPhotoLikeItem accountPhotoLikeItem = new AccountPhotoLikeItem();
        accountPhotoLikeItem.setAccountMadeLike(accountItem);
        accountPhotoLikeItem.setPhoto(accountPhotoItem);
        getSessionFactory().getCurrentSession().save(accountPhotoLikeItem);
    }

}
