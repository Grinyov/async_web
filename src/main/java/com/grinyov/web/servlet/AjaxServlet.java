package com.grinyov.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Created by vgrinyov.
 */
public class AjaxServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SimpleServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String name = request.getParameter("username");
        if (name == null || name.trim().isEmpty()) {
            writer.write("<p style=\"color: red;\">Input correct name!</p>");
        } else {
            writer.printf("<p style=\"color: green;\">Hello, %s!</p>", name);
        }
        writer.flush();
    }
}
