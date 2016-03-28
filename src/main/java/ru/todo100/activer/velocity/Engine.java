package ru.todo100.activer.velocity;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * @author Igor Bobko <limit-speeed@yandex.ru>.
 */
public class Engine extends VelocityEngineFactoryBean {
    ServletContext servletContext;
    public Engine(ServletContext servletContext){
        super();
        this.servletContext = servletContext;
    }

    protected VelocityEngine newVelocityEngine() throws IOException, VelocityException {
        VelocityEngine engine = new VelocityEngine();
        engine.setApplicationAttribute("javax.servlet.ServletContext", servletContext);
        return engine;
    }
}
