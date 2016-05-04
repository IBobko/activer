package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.DisputeMessageDao;
import ru.todo100.activer.dao.HappenedDisputeDao;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.DisputeMessageItem;
import ru.todo100.activer.model.HappenedDisputeItem;

import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DisputeHandler extends AbstractMessageHandler {

    private DisputeMessageDao disputeMessageDao;
    private HappenedDisputeDao happenedDisputeDao;
    private AccountDao accountService;

    public DisputeMessageDao getDisputeMessageDao() {
        return disputeMessageDao;
    }

    @Autowired
    public void setDisputeMessageDao(DisputeMessageDao disputeMessageDao) {
        this.disputeMessageDao = disputeMessageDao;
    }

    public HappenedDisputeDao getHappenedDisputeDao() {
        return happenedDisputeDao;
    }

    @Autowired
    public void setHappenedDisputeDao(HappenedDisputeDao happenedDisputeDao) {
        this.happenedDisputeDao = happenedDisputeDao;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(ReceiveMessageData message, final Principal principal) {
        final HappenedDisputeItem happenedDisputeItem = (HappenedDisputeItem) getHappenedDisputeDao().get(message.getTo());

        final AccountItem account = accountService.get(principal.getName());
        final AccountItem opponent;
        if (happenedDisputeItem.getAccountAppliedId().equals(account.getId())) {
            opponent = accountService.get(happenedDisputeItem.getAccountInitId());
        } else {
            opponent = accountService.get(happenedDisputeItem.getAccountAppliedId());
        }

        final PacketMessageData messageData = new PacketMessageData();
        messageData.setFrom(generateAccountData(account));
        messageData.setDate(new GregorianCalendar());
        messageData.setType(PopupMessageType.DISPUTE_MESSAGE);
        messageData.setMessage(message.getMessage());

        final DisputeMessageItem disputeMessageItem = new DisputeMessageItem();
        disputeMessageItem.setAccountId(account.getId());
        disputeMessageItem.setDisputeId(happenedDisputeItem.getId());
        disputeMessageItem.setMessage(message.getMessage());
        disputeMessageItem.setSentDate(new GregorianCalendar());

        getDisputeMessageDao().save(disputeMessageItem);

        getTemplate().convertAndSendToUser(opponent.getUsername(), "/global2", messageData);
        getTemplate().convertAndSendToUser(principal.getName(), "/global2", messageData);

    }
}
