package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.WallDao;
import ru.todo100.activer.data.MessageData;
import ru.todo100.activer.data.ReceiveWallData;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.WallItem;
import ru.todo100.activer.populators.WallPopulator;
import ru.todo100.activer.service.NewsService;

import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Controller
@RequestMapping("/wall")
public class WallController {
    private AccountDao accountService;
    private WallPopulator wallPopulator;
    private WallDao wallService;
    private NewsService newsService;

    public WallPopulator getWallPopulator() {
        return wallPopulator;
    }

    @Autowired
    public void setWallPopulator(WallPopulator wallPopulator) {
        this.wallPopulator = wallPopulator;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    public WallDao getWallService() {
        return wallService;
    }

    @Autowired
    public void setWallService(WallDao wallService) {
        this.wallService = wallService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping("/publish")
    @ResponseBody
    public MessageData publish(final ReceiveWallData receiveWallData, final BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            final AccountItem account = getAccountService().get(receiveWallData.getId());
            final AccountItem currentAccount = getAccountService().getCurrentAccount();
            WallItem post = new WallItem();
            post.setAccount(account);
            post.setText(receiveWallData.getText());
            post.setAddedDate(new GregorianCalendar());
            post.setSender(currentAccount.getId());
            getWallService().save(post);
            getNewsService().addNews(currentAccount.getId(), "WALL", receiveWallData.getText());
            return getWallPopulator().populate(post);
        }
        return null;
    }
}



