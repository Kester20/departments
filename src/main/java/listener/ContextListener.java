package listener;

import dao.DaoFactory;
import dao.HibernateSessionFactory;
import service.impl.DepartmentServiceImpl;
import service.impl.EmployeeServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;

/**
 * @author Arsalan
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DaoFactory.initDaoFactory();
        HibernateSessionFactory.buildSessionFactory();
        servletContext.setAttribute(DEPARTMENT_SERVICE, new DepartmentServiceImpl());
        servletContext.setAttribute(EMPLOYEE_SERVICE, new EmployeeServiceImpl());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
