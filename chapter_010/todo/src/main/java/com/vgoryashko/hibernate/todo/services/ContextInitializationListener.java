package com.vgoryashko.hibernate.todo.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Class that implements application context initialization listener.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/20/18
 */
public class ContextInitializationListener implements ServletContextListener {

    /**
     * Receives notification that the web application initialization
     * process is starting.
     * <p>
     * <p>During application initialization created SessionFactory object
     * and set it as the attribute to ServletContext object.
     * <p>
     * <p>All ServletContextListeners are notified of context
     * initialization before any filters or servlets in the web
     * application are initialized.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("factory", factory);
    }

    /**
     * Receives notification that the ServletContext is about to be
     * shut down.
     * <p>
     * <p>Closes SessionFactory object when the application is about to stop.
     * <p>
     * <p>All servlets and filters will have been destroyed before any
     * ServletContextListeners are notified of context
     * destruction.
     *
     * @param sce the ServletContextEvent containing the ServletContext
     *            that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((SessionFactory) sce.getServletContext().getAttribute("factory")).close();
    }
}
