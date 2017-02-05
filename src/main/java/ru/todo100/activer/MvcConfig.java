package ru.todo100.activer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions("classpath:/tiles/tiles-templates.xml");
        configurer.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
        return configurer;
    }

    @Bean
    public TilesViewResolver viewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
        return viewResolver;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new org.springframework.web.multipart.support.StandardServletMultipartResolver();
    }
}
