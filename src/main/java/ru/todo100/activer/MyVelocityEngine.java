package ru.todo100.activer;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class MyVelocityEngine extends VelocityEngineFactoryBean {
    ServletContext servletContext;
    public MyVelocityEngine(ServletContext servletContext){
        super();
        this.servletContext = servletContext;
    }

    protected VelocityEngine newVelocityEngine() throws IOException, VelocityException {
        VelocityEngine engine = new VelocityEngine();
        engine.setApplicationAttribute("javax.servlet.ServletContext", servletContext);
        return engine;
    }
}
