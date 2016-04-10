package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.PopupMessageType;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.MessageAccountData;
import ru.todo100.activer.data.PacketMessageData;
import ru.todo100.activer.model.AccountItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/dating")
public class DatingPageController {

    @Autowired
    private SimpMessagingTemplate template;


    private AccountDao accountDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("pageType","dating");
        try {
            Integer dialog = Integer.parseInt(request.getParameter("dialog"));
            model.addAttribute("dialog", dialog);
        } catch (NumberFormatException ignored) {
        }
        model.addAttribute("templatePost", MessageController.generateTemplateMessageData());
        return "dating/index";
    }

    @RequestMapping("/flirt")
    public String flirt(HttpServletRequest request, Model model) {
        model.addAttribute("pageType","dating");
        try {
            Integer dialog = Integer.parseInt(request.getParameter("dialog"));
            model.addAttribute("dialog", dialog);
        } catch (NumberFormatException ignored) {
        }
        model.addAttribute("templatePost", MessageController.generateTemplateMessageData());
        return "dating/flirt";
    }

    @RequestMapping("/dispute")
    public String dispute(HttpServletRequest request, Model model) {
        model.addAttribute("pageType","dating");
        try {
            Integer dialog = Integer.parseInt(request.getParameter("dialog"));
            model.addAttribute("dialog", dialog);
        } catch (NumberFormatException ignored) {
        }
        model.addAttribute("templatePost", MessageController.generateTemplateMessageData());
        return "dating/dispute";
    }



    @ResponseBody
    @RequestMapping("/search")
    public void search(HttpServletResponse response) throws IOException {
        AccountItem account = getAccountDao().getRandomOnlineAccount();

        final PacketMessageData messageData = new PacketMessageData();
        messageData.setType(PopupMessageType.DATING);

        MessageAccountData from = new MessageAccountData();
        from.setId(account.getId());
        messageData.setFrom(from);

        template.convertAndSendToUser(account.getUsername(), "/global2", messageData);


        response.getWriter().println(from.getId());

    }

}
