package com.aimprosoft.noormal.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * @author Arsalan
 */
public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {
        WebApplicationContext webApplicationContext = initApplicationConfig(container);
        initDispatcherServlet(container, webApplicationContext);
        initEncodingFilter(container);
    }

    private WebApplicationContext initApplicationConfig(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.setServletContext(container);
        return ctx;
    }

    private void initDispatcherServlet(ServletContext container, WebApplicationContext ctx) {
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

    private void initEncodingFilter(ServletContext container) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        FilterRegistration.Dynamic characterEncoding = container.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }

}
