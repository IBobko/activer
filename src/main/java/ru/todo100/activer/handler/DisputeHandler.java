package ru.todo100.activer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.*;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.data.ReceiveMessageData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.DisputeMessageItem;
import ru.todo100.activer.model.GiftItem;
import ru.todo100.activer.model.HappenedDisputeItem;
import ru.todo100.activer.service.BalanceService;
import java.security.Principal;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DisputeHandler extends AbstractMessageHandler {

    @Value("${static.host.files}")
    private String staticHost;

    public BalanceService getBalanceService() {
        return balanceService;
    }
    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Autowired
    private GiftDao giftDao;

    @Autowired
    private AccountGiftDao accountGiftDao;

    private BalanceService balanceService;
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
        final PacketMessageData messageData = new PacketMessageData();
        final AccountItem account = accountService.get(principal.getName());
        final AccountItem opponent;
        if (happenedDisputeItem.getAccountAppliedId().equals(account.getId())) {
            opponent = accountService.get(happenedDisputeItem.getAccountInitId());
        } else {
            opponent = accountService.get(happenedDisputeItem.getAccountAppliedId());
        }

        String messageText = message.getMessage();
        if (messageText.startsWith("gift:")) {
            final PacketMessageData spentMessage = new PacketMessageData();
            spentMessage.setType(PopupMessageType.SPENT);
            spentMessage.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(new GregorianCalendar().getTime()));
            Integer giftId = Integer.parseInt(messageText.substring(messageText.indexOf("gift:") + 5));
            GiftItem gift = (GiftItem) giftDao.get(giftId);
            if (getBalanceService().subtractAccountBalanceSum(account.getId(), gift.getCost(), "Оплата подарка")) {
                messageText = "<img src='" + staticHost + "/" + gift.getFile() + ".'/>";
                spentMessage.setMessage(gift.getCost().toString());
                accountGiftDao.give(account.getId(),opponent.getId(),giftId,"Из споров");
            } else {
                spentMessage.setMessage("0");
            }
            getTemplate().convertAndSendToUser(principal.getName(), "/global2", spentMessage);
            if (spentMessage.getMessage().equals("0")) {
                return;
            }
        }

        messageData.setFrom(generateAccountData(account));
        messageData.setDate(FORMAT_DD_MM_yyyy_HH_mm_ss.format(new GregorianCalendar().getTime()));
        messageData.setType(PopupMessageType.DISPUTE_MESSAGE);
        messageData.setMessage(messageText);
        messageData.setInterlocutor(happenedDisputeItem.getId());
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
