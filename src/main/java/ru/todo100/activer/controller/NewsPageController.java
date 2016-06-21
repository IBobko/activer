package ru.todo100.activer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.data.FriendData;
import ru.todo100.activer.data.NewsData;
import ru.todo100.activer.qualifier.Qualifier;
import ru.todo100.activer.form.PagedForm;
import ru.todo100.activer.model.NewsItem;
import ru.todo100.activer.populators.NewsPopulator;
import ru.todo100.activer.service.FriendsService;
import ru.todo100.activer.service.NewsService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    private NewsPopulator newsPopulator;
    @RequestMapping("/")
    public String index(final HttpSession session,final Model model) {
        final List<Integer> accounts = getAccounts(session);
        final List<NewsItem> news = getNewsService().getNews(accounts);
        final List<NewsData> newsData = new ArrayList<>();
        for (NewsItem n:news){
            newsData.add(newsPopulator.populate(n));
        }
        model.addAttribute("news",newsData);
        return "news/index";
    }


    public List<Integer> getAccounts(final HttpSession session){
        final List<Integer> accounts = new ArrayList<>();
        final List<FriendData> friends = friendsService.getFriendData(session).getFriends();
        final List<FriendData> outRequest = friendsService.getFriendData(session).getOutRequest();
        for (FriendData friendData: friends) {
            accounts.add(friendData.getId());
        }

        for (FriendData friendData: outRequest) {
            accounts.add(friendData.getId());
        }

        final Integer currentAccount = accountService.getCurrentProfileData(session).getId();
        accounts.add(currentAccount);
        return accounts;
    }

    public final static Integer COUNT_PER_PAGE = 2;

    @RequestMapping("/ajax")
    @ResponseBody
    public List<NewsData> ajax(final PagedForm pagedForm,final HttpSession session){
        final Qualifier qualifier = new Qualifier();
        qualifier.setCount(COUNT_PER_PAGE);
        qualifier.setStart(pagedForm.getPage() * COUNT_PER_PAGE);
        qualifier.setOrder(Qualifier.Order.desc);
        qualifier.setOrderName("id");
        HashMap<String, Object> params = new HashMap<>();
        params.put("accounts",getAccounts(session));
        qualifier.setParams(params);
        final List<NewsItem> news =  getNewsService().getByQualifier(qualifier);
        final List<NewsData> newsData = new ArrayList<>();
        for (NewsItem n:news){
            newsData.add(newsPopulator.populate(n));
        }
        return newsData;
    }

}

