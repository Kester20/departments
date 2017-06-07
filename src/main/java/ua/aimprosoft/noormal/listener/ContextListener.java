package ua.aimprosoft.noormal.listener;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ua.aimprosoft.noormal.action.ActionFactory;
import ua.aimprosoft.noormal.config.ApplicationConfig;
import ua.aimprosoft.noormal.controller.Controller;
import ua.aimprosoft.noormal.dao.DaoFactory;
import ua.aimprosoft.noormal.dao.HibernateSessionFactory;
import ua.aimprosoft.noormal.service.impl.DepartmentServiceImpl;
import ua.aimprosoft.noormal.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static ua.aimprosoft.noormal.util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static ua.aimprosoft.noormal.util.Constants.ContextConstants.EMPLOYEE_SERVICE;

/**
 * @author Arsalan
 */
@WebListener
public class ContextListener implements ServletContextListener {

    ContextLoader contextLoader = new ContextLoader();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DaoFactory.initDaoFactory();
        HibernateSessionFactory.buildSessionFactory();
        servletContext.setAttribute(DEPARTMENT_SERVICE, new DepartmentServiceImpl());
        servletContext.setAttribute(EMPLOYEE_SERVICE, new EmployeeServiceImpl());

        servletContext.setInitParameter ( "contextClass",
                "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        servletContext.setInitParameter ( "contextConfigLocation", "ua.aimprosoft.noormal");

        //WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ActionFactory actionFactory = applicationContext.getBean(ActionFactory.class);
        servletContext.setAttribute("a", actionFactory);
        //AutowireCapableBeanFactory acbf = applicationContext.getAutowireCapableBeanFactory();

        /*Controller controller = new Controller();
        acbf.autowireBean(controller);*/

        /*ActionFactory actionFactory = new ActionFactory();
        acbf.autowireBean(actionFactory);*/

        //WebApplicationContext context = contextLoader.initWebApplicationContext (servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
