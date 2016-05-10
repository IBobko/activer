package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.DialogData;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;
import ru.todo100.activer.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@SuppressWarnings("Duplicates")
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private PhotoService photoService1;
    @Autowired
    private MessageDao messageService;
    @Autowired
    private AccountDao accountService;

    public static MessageData generateTemplateMessageData() {
        final MessageData template = new MessageData();
        template.setText("%text%");
        final MessageAccountData sender = new MessageAccountData();
        sender.setFirstName("%firstName%");
        sender.setLastName("%lastName%");
        template.setDate("%date%");
        template.setSender(sender);
        return template;
    }

    @RequestMapping
    public String index(final Model model) {
        model.addAttribute("pageType", "message");
        final AccountItem accountItem = accountService.getCurrentAccount();
        final List<MessageItem> messageItems = messageService.getDialogs(accountItem.getId());

        final List<DialogData> dialogDataList = new ArrayList<>();

        for (final MessageItem messageItem : messageItems) {
            final MessageData messageData = new MessageData();
            final AccountItem from = accountService.get(messageItem.getAccountFrom());
            final MessageAccountData sender = messageAccountDataPopulator(from);
            messageData.setSender(sender);
            messageData.setText(messageItem.getText());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            messageData.setDate(format.format(messageItem.getAddedDate().getTime()));
            DialogData dialogData = new DialogData();
            dialogDataList.add(dialogData);
            dialogData.setLastMessage(messageData);

            if (messageItem.getAccountTo().equals(accountItem.getId())) {

                dialogData.setOwner(sender);
            } else {
                final AccountItem toAccountItem = accountService.get(messageItem.getAccountTo());
                dialogData.setOwner(messageAccountDataPopulator(toAccountItem));
            }
        }
        model.addAttribute("dialogs", dialogDataList);
        return "message/index";
    }

    @RequestMapping("/ajax/{id:\\d+}")
    @ResponseBody
    public List<MessageData> ajaxMessage(@PathVariable final Integer id, final HttpServletRequest request) {
        final ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
        final List<MessageItem> messageItems = messageService.getDialog(id, profileData.getId());
        final List<MessageData> messageData = messagePopulate(messageItems);
        Collections.reverse(messageData);
        return messageData;
    }

    private List<MessageData> messagePopulate(List<MessageItem> messageItems) {
        final Map<Integer, MessageAccountData> cachedAccountData = new HashMap<>();
        final List<MessageData> messageData = new ArrayList<>();
        for (MessageItem item : messageItems) {
            final MessageData data = new MessageData();
            if (cachedAccountData.containsKey(item.getAccountFrom())) {
                data.setSender(cachedAccountData.get(item.getAccountFrom()));
            } else {
                final AccountItem from = accountService.get(item.getAccountFrom());
                final MessageAccountData sender = new MessageAccountData();
                sender.setFirstName(from.getFirstName());
                sender.setLastName(from.getLastName());
                sender.setPhoto60x60(photoService1.getSizedPhoto((item.getAccountFrom())).getPhotoMini());
                data.setSender(sender);
                cachedAccountData.put(item.getAccountFrom(), sender);
            }
            data.setText(item.getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            data.setDate(format.format(item.getAddedDate().getTime()));
            messageData.add(data);
        }
        return messageData;
    }

    @ResponseBody
    @RequestMapping("/search")
    public List<DialogData> search(@RequestParam(name = "t") String t, final HttpServletRequest request) {
        final ProfileData profileData = accountService.getCurrentProfileData(request.getSession());
        final List<MessageItem> messageItems = messageService.searchDialog(profileData.getId(), t);

        final AccountItem accountItem = accountService.getCurrentAccount();

        final List<DialogData> dialogDataList = new ArrayList<>();

        for (final MessageItem messageItem : messageItems) {
            final MessageData messageData = new MessageData();
            final AccountItem from = accountService.get(messageItem.getAccountFrom());
            final MessageAccountData sender = messageAccountDataPopulator(from);
            messageData.setSender(sender);
            messageData.setText(messageItem.getText());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            messageData.setDate(format.format(messageItem.getAddedDate().getTime()));
            DialogData dialogData = new DialogData();
            dialogDataList.add(dialogData);
            dialogData.setLastMessage(messageData);
            if (messageItem.getAccountTo().equals(accountItem.getId())) {
                dialogData.setOwner(sender);
            } else {
                final AccountItem toAccountItem = accountService.get(messageItem.getAccountTo());
                dialogData.setOwner(messageAccountDataPopulator(toAccountItem));
            }
        }


        return dialogDataList;
    }

    private MessageAccountData messageAccountDataPopulator(AccountItem accountItem) {
        final MessageAccountData messageAccountDataPopulator = new MessageAccountData();
        messageAccountDataPopulator.setFirstName(accountItem.getFirstName());
        messageAccountDataPopulator.setLastName(accountItem.getLastName());
        messageAccountDataPopulator.setId(accountItem.getId());
        messageAccountDataPopulator.setPhoto60x60(photoService1.getSizedPhoto(accountItem.getId()).getPhotoMini());
        messageAccountDataPopulator.setOnline(accountItem.getIsOnline());
        return messageAccountDataPopulator;
    }

}
