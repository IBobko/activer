package ru.todo100.activer.tags;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
                initializer(var,"");
                pageContext.setAttribute(this.varName, var);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.doEndTag();
    }

    @SuppressWarnings("unchecked")
    public void initializer(Object o, String path) {
        for (Method method : o.getClass().getMethods())
            if (method.getName().startsWith("get")) {
                String fieldName = method.getName().substring(3);
                if (fieldName.equals("Class")) continue;
                try {
                    Method setter = o.getClass().getMethod("set" + fieldName, method.getReturnType());
                    final String Path = path + fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
                    final Object embedded;
                    if (method.getReturnType().isAssignableFrom(String.class)) {
                        embedded = "#" + Path;
                    } else if (method.getReturnType().isAssignableFrom(Number.class) || method.getReturnType().isAssignableFrom(Calendar.class)) {
                        Enhancer enhancer = new Enhancer();
                        enhancer.setSuperclass(method.getReturnType());
                        enhancer.setCallback(new MethodInterceptor() {
                            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                                return "#" + Path;
                            }
                        });
                        embedded = enhancer.create();
                    } else if (method.getReturnType().isAssignableFrom(Boolean.class)) {
                        embedded = null;
                    } else {
                        embedded = method.getReturnType().newInstance();
                        initializer(embedded, Path + "_");
                    }
                    setter.invoke(o, embedded);

                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
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
