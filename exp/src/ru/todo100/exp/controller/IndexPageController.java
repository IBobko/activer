package ru.todo100.exp.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Игорь on 08.11.2015.
 */
public class IndexPageController implements javax.servlet.Servlet {
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        servletResponse.getWriter().println("Hello World!!!");
        servletResponse.getWriter().println(getServletConfig().getServletContext().getRequestDispatcher("/index.jsp"));
        servletResponse.getWriter().println(servletRequest.getRequestDispatcher("/index.jsp"));

//        servletResponse.getWriter().println("Hello World!!!");
//        servletResponse.getWriter().println("Hello World!!!");
//
        servletRequest.getRequestDispatcher("/index.jsp").forward(servletRequest,servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
