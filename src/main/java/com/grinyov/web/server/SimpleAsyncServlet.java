package com.grinyov.web.server;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
@WebServlet(value = "/async-simple", asyncSupported = true)
public class SimpleAsyncServlet extends HttpServlet {

    // TODO: set to 'true' for emulate long time operations
    private static final boolean debug = false;
    private static final int ms = 2000;

    private static final Logger LOGGER = Logger.getLogger(SimpleAsyncServlet.class.getName());

    private volatile Thread thread;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // start async
        final AsyncContext asyncContext = request.startAsync();

        thread = new Thread(() -> {
            try {
                if (debug) {
                    Thread.sleep(ms);
                }

                // using async response
                ServletResponse asyncResponse = asyncContext.getResponse();
                asyncResponse.setContentType("text/plain");
                PrintWriter out = asyncResponse.getWriter();
                out.printf("Task completed by %s.", Thread.currentThread().getName());
                out.flush();
            } catch (InterruptedException | IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } finally {
                asyncContext.complete();
            }
        });
        thread.start();
    }

}