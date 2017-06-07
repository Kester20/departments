/*
package ua.aimprosoft.noormal.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ua.aimprosoft.noormal.action.ActionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

*/
/**
 * @author Arsalan. Created on 07.06.2017.
 *//*

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        */
/*AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
        rootContext.refresh();

        ActionFactory actionFactory = rootContext.getBean(ActionFactory.class);
        actionFactory.toString();

        // Create the dispatcher servlet's Spring application context
        */
/*AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");*//*

    }

}
*/
