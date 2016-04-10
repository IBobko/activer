package ru.todo100.activer.controller;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.data.DialogData;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ProfileData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.MessageItem;
import ru.todo100.activer.populators.ProfilePopulator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageDao messageService;

    @Autowired
    private AccountDao accountService;

    @Autowired
    private ProfilePopulator profilePopulator;

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

    @RequestMapping("/message")
    public String index(final Model model) {
        model.addAttribute("pageType","message");
        final AccountItem accountItem = accountService.getCurrentAccount();
        final List<MessageItem> messageItems = messageService.getDialogs(accountItem.getId());

        final List<DialogData> dialogData = new ArrayList<>();

        for (MessageItem item : messageItems) {
            final MessageData data = new MessageData();
            final AccountItem from = accountService.get(item.getAccountFrom());
            final MessageAccountData sender = new MessageAccountData();
            sender.setFirstName(from.getFirstName());
            sender.setLastName(from.getLastName());
            sender.setId(item.getAccountFrom());
            data.setSender(sender);
            data.setText(item.getText());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            data.setDate(format.format(item.getAddedDate().getTime()));
            DialogData dd = new DialogData();
            dialogData.add(dd);
            dd.setLastMessage(data);

            if (item.getAccountTo().equals(accountItem.getId())) {

                dd.setOwner(sender);
            } else {
                final AccountItem to = accountService.get(item.getAccountTo());
                final MessageAccountData to1 = new MessageAccountData();
                to1.setFirstName(to.getFirstName());
                to1.setLastName(to.getLastName());
                to1.setId(item.getAccountTo());
                dd.setOwner(to1);
            }

        }

        model.addAttribute("dialogs", dialogData);

        return "message/index";
    }

    @RequestMapping("/message/{id:\\d+}")
    public String profileMessage(final Model model, @PathVariable Integer id) {
        model.addAttribute("pageType","message");

        /** Добавить кэширование **/
        final List<MessageData> messageData = new ArrayList<>();
        AccountItem accountItem = accountService.getCurrentAccount();
        List<MessageItem> messageItems = messageService.getDialog(id, accountItem.getId());
        for (MessageItem item : messageItems) {
            final MessageData data = new MessageData();
            final AccountItem from = accountService.get(item.getAccountFrom());
            final MessageAccountData sender = new MessageAccountData();
            sender.setFirstName(from.getFirstName());
            sender.setLastName(from.getLastName());
            data.setSender(sender);
            data.setText(item.getText());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
            data.setDate(format.format(item.getAddedDate().getTime()));
            messageData.add(data);
        }

        final AccountItem friend = accountService.get(id);
        ProfileData friendData = profilePopulator.populate(friend);
        model.addAttribute("friend", friendData);
        model.addAttribute("myProfile", accountService.getCurrentAccount());


        Collections.reverse(messageData);

        model.addAttribute("lastMessages", messageData);
        model.addAttribute("templatePost", generateTemplateMessageData());
        return "message/communicate";
    }
}
