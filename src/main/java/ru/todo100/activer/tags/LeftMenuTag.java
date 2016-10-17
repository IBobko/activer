package ru.todo100.activer.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class LeftMenuTag extends BodyTagSupport {

//    @Override
//    public int doStartTag() throws JspException {
//
//        String pageType = (String)pageContext.getRequest().getAttribute("pageType");
//        if (pageType!=null) {
//            String[] sections = pageType.split("/");
//            if (sections[0].equals("admin")) {
//                if (sections.length > 1) {
//                    pageContext.setAttribute(sections[1]+"Menu"," class='active'");
//                }
//            }
//
//        }
//
//        return EVAL_BODY_INCLUDE;
//    }


    @Override
    public int doEndTag() throws JspException {

        String pageType = (String)pageContext.getRequest().getAttribute("pageType");
        if (pageType!=null) {
            String[] sections = pageType.split("/");
            if (sections[0].equals("admin")) {
                if (sections.length > 1) {

                    pageContext.getRequest().setAttribute(sections[1]+"Menu"," class='active'");

                    //pageContext.setAttribute(sections[1]+"Menu"," class='active'");
                } else {
                    pageContext.getRequest().setAttribute("creatorMenu"," class='active'");
                    pageContext.setAttribute("creatorMenu"," class='active'");
                }
                pageContext.getRequest().setAttribute("mainPage",sections[0]);
            } else {
                final StringBuilder menuName = new StringBuilder();
                for (String sub: sections) {
                    menuName.append(sub.substring(0, 1).toUpperCase()).append(sub.substring(1));
                }
                pageContext.getRequest().setAttribute(menuName.toString() + "Menu"," class='active'");
                pageContext.getRequest().setAttribute("mainPage",sections[0]);
            }

        }

        return super.doEndTag();
    }

    @Override
    public void doInitBody() throws JspException {

    }

    @Override
    public int doAfterBody() throws JspException {
        return 0;
    }

}
