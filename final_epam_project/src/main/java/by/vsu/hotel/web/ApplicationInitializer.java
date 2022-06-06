package by.vsu.hotel.web;

import by.vsu.hotel.dao.mysql.connector.Connector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ServletContext context = event.getServletContext();
            String jdbcDriver = context.getInitParameter("jdbc-driver");
            String jdbcUrl = context.getInitParameter("jdbc-url");
            String jdbcUser = context.getInitParameter("jdbc-user");
            String jdbcPassword = context.getInitParameter("jdbc-password");
            Connector.init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
            System.out.println("DEBUG: APPLICATION INIT OK");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
