package com.aimprosoft.noormal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Arsalan
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.aimprosoft.noormal", "net.sf.oval.integration.spring"})
public class ApplicationConfig {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
