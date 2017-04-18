package com.grinyov.web.task;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
public class AsyncProcess implements Runnable {

    // TODO: set to 'true' for emulate long time operations
    private static final int actionsLimit = 10;
    private static final int ms = 500;

    private AsyncContext asyncContext;

    public AsyncProcess() {
    }

    public AsyncProcess(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        try {
            ServletResponse asyncResponse = asyncContext.getResponse();
            asyncResponse.setContentType("text/html");
            PrintWriter out = asyncResponse.getWriter();
            out.write("Processing begin!<br />");
            for (int action = 0; action < actionsLimit; action++) {
                longProcessing(ms);
                // using async response
                out.printf("Action #%d completed by %s.<br />", action, Thread.currentThread().getName());
                out.flush();
            }
            out.write("Processing complete!");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(AsyncProcess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //complete the processing
            asyncContext.complete();
        }
    }

    private void longProcessing(int secs) {
        try {
            Thread.sleep(secs);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
