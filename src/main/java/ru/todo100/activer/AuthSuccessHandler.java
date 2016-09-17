package ru.todo100.activer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.dao.MessageDao;
import ru.todo100.activer.service.FriendsService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private AccountDao accountService;
    private FriendsService friendsService;
    private MessageDao messageService;

    public FriendsService getFriendsService() {
        return friendsService;
    }

    @Autowired
    public void setFriendsService(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    public MessageDao getMessageService() {
        return messageService;
    }

    @Autowired
    public void setMessageService(MessageDao messageService) {
        this.messageService = messageService;
    }

    public AccountDao getAccountService() {
        return accountService;
    }

    @Autowired
    public void setAccountService(AccountDao accountService) {
        this.accountService = accountService;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
                                        final Authentication authentication) throws IOException, ServletException {
        getAccountService().initCurrentProfile(httpServletRequest.getSession());
        /*@todo возможно не стоит этот методо все таки вызывать здесь*/
        httpServletRequest.setAttribute("friendsData", getFriendsService().getFriendData(httpServletRequest.getSession()));
        httpServletRequest.getSession().setAttribute("unreadMessages", getMessageService().unreadCount( getAccountService().getCurrentProfileData(httpServletRequest.getSession()).getId()));
        if (httpServletRequest.getParameter("remember-me") != null) {
            Cookie cookie = new Cookie("remember-me", "1");
            cookie.setMaxAge(604800);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}
