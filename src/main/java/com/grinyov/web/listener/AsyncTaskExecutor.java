package com.grinyov.web.listener;

import com.grinyov.web.server.SimpleAsyncServlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
@WebListener
public class AsyncTaskExecutor implements ServletContextListener{

    // TODO: set to 'true' for emulate long time operations
    private static final boolean debug = false;
    private static final int ms = 2000;

    private static final Logger LOGGER = Logger.getLogger(AsyncTaskExecutor.class.getName());

    private static final BlockingQueue<AsyncContext> QUEUE = new LinkedBlockingQueue<>();

    private volatile Thread thread;

    public static void add(AsyncContext context) {
        QUEUE.add(context);
    }

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        thread = new Thread(() -> {
            while (true) {
                try {
                    if (debug) {
                        Thread.sleep(ms);
                    }

                    AsyncContext context;
                    while ((context = QUEUE.poll()) != null) {
                        try {
                            ServletResponse response = context.getResponse();
                            response.setContentType("text/plain");
                            PrintWriter out = response.getWriter();
                            out.printf("Task completed by %s.", Thread.currentThread().getName());
                            out.flush();
                        } catch (Exception ex) {
                            LOGGER.severe(ex.getMessage());
                        } finally {
                            context.complete();
                        }
                    }
                } catch (InterruptedException ex) {
                    LOGGER.severe(ex.getMessage());
                    return;
                }
            }
        });
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        thread.interrupt();
    }
}
