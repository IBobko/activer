package ru.todo100.activer.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import ru.todo100.activer.service.FriendsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DataPopulatorFilter extends GenericFilterBean {

    @Autowired
    private FriendsService friendsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            final FriendsService friendsService = (FriendsService) WebApplicationContextUtils.
                    getRequiredWebApplicationContext(getFilterConfig().getServletContext()).
                    getBean("friendsService");

            servletRequest.setAttribute("friendsData", friendsService.getFriendData(((HttpServletRequest) servletRequest).getSession()));
        } catch(Exception ignored){}
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch(Exception ignored){}
    }
}
