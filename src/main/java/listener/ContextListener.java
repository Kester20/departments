package listener;

import dao.DaoFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import static util.Constants.DbConstants.DATA_SOURCE_LOOKUP;

/**
 * @author Arsalan
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource dataSource = initDataSource();
        DaoFactory daoFactory = new DaoFactory(dataSource);
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
