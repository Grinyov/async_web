package com.grinyov.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vgrinyov.
 */
public class ExecutorPoolContextListener implements ServletContextListener {

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("executor", executor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executor.shutdown();
    }

}
