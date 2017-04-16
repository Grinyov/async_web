package com.grinyov.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 *
 * Example of simple servlet
 */
@WebServlet("/simple")
public class SimpleServlet extends HttpServlet {
    // TODO: set to true for emulate long time operations
    private static final boolean debug = false;
    private static final int timeout = 2000;

    private static final Logger LOGGER = Logger.getLogger(SimpleServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            long start = System.currentTimeMillis();
            if(debug){
                Thread.sleep(timeout);
            }
            long duration = System.currentTimeMillis() - start;
            response.setContentType("text/plain");
            response.getWriter().printf("Thread %s completed the task in %d ms", Thread.currentThread().getName(), duration);
        } catch (InterruptedException | IOException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        if (name == null || name.trim().isEmpty()){
            request.setAttribute("error", "Input correct name!");
        } else {
            request.setAttribute("message", String.format("Hello, %s", name));
        }
        request.getRequestDispatcher("simple.jsp").forward(request, response);
    }
}
