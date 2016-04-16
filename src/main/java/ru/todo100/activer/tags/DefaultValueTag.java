package ru.todo100.activer.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.lang.reflect.Method;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
public class DefaultValueTag extends TagSupport {
    private Class type;
    private Object var;
    private String varName;

    public void setVar(Object var) {
        this.var = var;
    }

    @Override
    public int doEndTag() throws JspException {
        if (this.var == null || !type.isInstance(var)) {
            try {
                var = type.newInstance();
                for (final Method method : type.getMethods()) {
                    if (method.getName().startsWith("get") && method.getTypeParameters().length == 0) {
                        final String field = method.getName().substring(3);
                        try {
                            final Method setter = type.getMethod("set" + field, method.getReturnType());
                            setter.invoke(var, "#" + field.substring(0, 1).toLowerCase() + field.substring(1));
                        } catch (Exception ignored) {
                        }
                    }
                }
                pageContext.setAttribute(this.varName, var);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.doEndTag();
    }

    public void setType(String type) {
        try {
            this.type = Class.forName(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}
