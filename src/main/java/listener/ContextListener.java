package listener;

import dao.DaoFactory;
import service.impl.DepartmentServiceImpl;
import service.impl.EmployeeServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.DbConstants.DATA_SOURCE_LOOKUP;

/**
 * @author Arsalan
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource dataSource = initDataSource();
        DaoFactory daoFactory = new DaoFactory(dataSource);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(DEPARTMENT_SERVICE, new DepartmentServiceImpl(daoFactory));
        servletContext.setAttribute(EMPLOYEE_SERVICE, new EmployeeServiceImpl(daoFactory));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private DataSource initDataSource() {
        Context initialContext;
        DataSource dataSource = null;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup(DATA_SOURCE_LOOKUP);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
