//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.hibernate.proxy.pojo.javassist;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Set;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyObject;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CoreLogging;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.type.CompositeType;

public class JavassistProxyFactory implements ProxyFactory, Serializable {
    private static final CoreMessageLogger LOG = CoreLogging.messageLogger(JavassistProxyFactory.class);
    private static final MethodFilter FINALIZE_FILTER = new MethodFilter() {
        public boolean isHandled(Method m) {
            return m.getParameterTypes().length != 0 || !m.getName().equals("finalize");
        }
    };
    private Class persistentClass;
    private String entityName;
    private Class[] interfaces;
    private Method getIdentifierMethod;
    private Method setIdentifierMethod;
    private CompositeType componentIdType;
    private boolean overridesEquals;
    private Class proxyClass;

    public JavassistProxyFactory() {
    }

    public void postInstantiate(String entityName, Class persistentClass, Set<Class> interfaces, Method getIdentifierMethod, Method setIdentifierMethod, CompositeType componentIdType) throws HibernateException {
        this.entityName = entityName;
        this.persistentClass = persistentClass;
        this.interfaces = this.toArray(interfaces);
        this.getIdentifierMethod = getIdentifierMethod;
        this.setIdentifierMethod = setIdentifierMethod;
        this.componentIdType = componentIdType;
        this.overridesEquals = ReflectHelper.overridesEquals(persistentClass);
        this.proxyClass = this.buildJavassistProxyFactory().createClass();
    }

    private Class[] toArray(Set<Class> interfaces) {
        return interfaces == null?ArrayHelper.EMPTY_CLASS_ARRAY:(Class[])interfaces.toArray(new Class[interfaces.size()]);
    }

    private javassist.util.proxy.ProxyFactory buildJavassistProxyFactory() {
        return buildJavassistProxyFactory(this.persistentClass, this.interfaces);
    }

    public static javassist.util.proxy.ProxyFactory buildJavassistProxyFactory(final Class persistentClass, Class[] interfaces) {
        javassist.util.proxy.ProxyFactory factory = new javassist.util.proxy.ProxyFactory() {
            protected ClassLoader getClassLoader() {
                return persistentClass.getClassLoader();
            }
        };
        factory.setSuperclass(interfaces.length == 1?persistentClass:null);
        factory.setInterfaces(interfaces);
        factory.setFilter(FINALIZE_FILTER);
        return factory;
    }

    public HibernateProxy getProxy(Serializable id, SessionImplementor session) throws HibernateException {
        JavassistLazyInitializer initializer = new JavassistLazyInitializer(this.entityName, this.persistentClass, this.interfaces, id, this.getIdentifierMethod, this.setIdentifierMethod, this.componentIdType, session, this.overridesEquals);

        try {
            HibernateProxy t = (HibernateProxy)this.proxyClass.newInstance();
            ((ProxyObject)t).setHandler(initializer);
            initializer.constructed();
            return t;
        } catch (Throwable var5) {
            LOG.error(LOG.javassistEnhancementFailed(this.entityName), var5);
            throw new HibernateException(LOG.javassistEnhancementFailed(this.entityName), var5);
        }
    }

    public static HibernateProxy deserializeProxy(SerializableProxy serializableProxy) {
        JavassistLazyInitializer initializer = new JavassistLazyInitializer(serializableProxy.getEntityName(), serializableProxy.getPersistentClass(), serializableProxy.getInterfaces(), serializableProxy.getId(), resolveIdGetterMethod(serializableProxy), resolveIdSetterMethod(serializableProxy), serializableProxy.getComponentIdType(), (SessionImplementor)null, ReflectHelper.overridesEquals(serializableProxy.getPersistentClass()));
        javassist.util.proxy.ProxyFactory factory = buildJavassistProxyFactory(serializableProxy.getPersistentClass(), serializableProxy.getInterfaces());

        try {
            Class t = factory.createClass();
            HibernateProxy message1 = (HibernateProxy)t.newInstance();
            ((Proxy)message1).setHandler(initializer);
            initializer.constructed();
            return message1;
        } catch (Throwable var5) {
            String message = LOG.javassistEnhancementFailed(serializableProxy.getEntityName());
            LOG.error(message, var5);
            throw new HibernateException(message, var5);
        }
    }

    private static Method resolveIdGetterMethod(SerializableProxy serializableProxy) {
        if(serializableProxy.getIdentifierGetterMethodName() == null) {
            return null;
        } else {
            try {
                return serializableProxy.getIdentifierGetterMethodClass().getDeclaredMethod(serializableProxy.getIdentifierGetterMethodName(), new Class[0]);
            } catch (NoSuchMethodException var2) {
                throw new HibernateException(String.format(Locale.ENGLISH, "Unable to deserialize proxy [%s, %s]; could not locate id getter method [%s] on entity class [%s]", new Object[]{serializableProxy.getEntityName(), serializableProxy.getId(), serializableProxy.getIdentifierGetterMethodName(), serializableProxy.getIdentifierGetterMethodClass()}));
            }
        }
    }

    private static Method resolveIdSetterMethod(SerializableProxy serializableProxy) {
        if(serializableProxy.getIdentifierSetterMethodName() == null) {
            return null;
        } else {
            try {
                return serializableProxy.getIdentifierSetterMethodClass().getDeclaredMethod(serializableProxy.getIdentifierSetterMethodName(), serializableProxy.getIdentifierSetterMethodParams());
            } catch (NoSuchMethodException var2) {
                throw new HibernateException(String.format(Locale.ENGLISH, "Unable to deserialize proxy [%s, %s]; could not locate id setter method [%s] on entity class [%s]", new Object[]{serializableProxy.getEntityName(), serializableProxy.getId(), serializableProxy.getIdentifierSetterMethodName(), serializableProxy.getIdentifierSetterMethodClass()}));
            }
        }
    }
}
