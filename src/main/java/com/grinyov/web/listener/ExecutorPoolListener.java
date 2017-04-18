package com.grinyov.web.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
public class ExecutorPoolListener implements AsyncListener {

    private static final Logger LOGGER = Logger.getLogger(ExecutorPoolListener.class.getName());

    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        LOGGER.info("ExecutorPoolListener has completed task");
        // we can do resource cleanup activity here
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        LOGGER.info("ExecutorPoolListener is calling error");
        //we can return error response to client
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        LOGGER.info("ExecutorPoolListener is starting task");
        //we can log the event here
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        LOGGER.info("ExecutorPoolListener onTimeout");
        //we can send appropriate response to client
        ServletResponse response = asyncEvent.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write("TimeOut Error in Processing");
        out.flush();
    }
}