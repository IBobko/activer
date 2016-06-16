package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.FriendData;
import ru.todo100.activer.data.NewsData;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.populators.NewsPopulator;
import ru.todo100.activer.service.FriendsService;
import ru.todo100.activer.service.NewsService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
@Controller
@RequestMapping("/news")
public class NewsPageController {

    @Autowired
    private AccountDao accountService;

    public NewsService getNewsService() {
        return newsService;
    }
    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @Autowired
    private FriendsService friendsService;


    private NewsService newsService;

    @Autowired
    NewsPopulator newsPopulator;
    @RequestMapping("/")
    public String index(final HttpSession session,final Model model) {

        List<FriendData> friends = friendsService.getFriendData1(session).getFriends();
        List<FriendData> friends1 = friendsService.getFriendData1(session).getOutRequest();

        List<Integer> accounts = new ArrayList<>();

        for (FriendData friendData: friends) {
            accounts.add(friendData.getId());
        }

        for (FriendData friendData: friends1) {
            accounts.add(friendData.getId());
        }

        Integer currentAccount = accountService.getCurrentProfileData(session).getId();
        accounts.add(currentAccount);


        List<NewsItem> news = getNewsService().getNews(accounts);

        List<NewsData> newsDatas = new ArrayList<>();

        for (NewsItem n:news){
            newsDatas.add(newsPopulator.populate(n));
        }

        model.addAttribute("news",newsDatas);
        return "news/index";
    }

}

