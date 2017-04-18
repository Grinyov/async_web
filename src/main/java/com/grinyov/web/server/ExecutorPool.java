package com.grinyov.web.server;

import com.grinyov.web.listener.ExecutorPoolListener;
import com.grinyov.web.task.AsyncProcess;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
@WebServlet(urlPatterns = "/executor-pool",asyncSupported = true)
public class ExecutorPool extends HttpServlet {

    private static final int asyncTimeout = 10000;

    private static final Logger LOGGER = Logger.getLogger(ExecutorPool.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Start {0}.", Thread.currentThread().getName());

        AsyncContext asyncContext = request.startAsync();
        asyncContext.addListener(new ExecutorPoolListener());
        asyncContext.setTimeout(asyncTimeout);

        ExecutorService executor = (ExecutorService) request.getServletContext().getAttribute("executor");

        executor.submit(new AsyncProcess(asyncContext));
        long endTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "End {0}. Elapsed: {1} ms.", new Object[]{Thread.currentThread().getName(), endTime - startTime});
    }

}
