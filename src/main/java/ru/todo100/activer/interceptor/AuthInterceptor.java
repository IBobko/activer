package ru.todo100.activer.interceptor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, Object o) throws Exception {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return true;
        }
        if (securityContext.getAuthentication() != null && securityContext.getAuthentication().isAuthenticated()) {
            for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS")) return true;
            }
            httpServletRequest.getRequestDispatcher("/profile").forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
