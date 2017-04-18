package com.grinyov.web.server;

import com.grinyov.web.listener.AsyncTaskExecutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vgrinyov.
 */
@WebServlet(value = "/async-executor", asyncSupported = true)
public class ExecutorAsync extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        AsyncTaskExecutor.add(request.startAsync());
    }
}
