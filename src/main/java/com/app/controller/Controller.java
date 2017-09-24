package com.app.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Controller", urlPatterns={"/"})
public class Controller extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String task = getClassPath(request);
        
        try {
            Class<?> type = Class.forName(task);
            Task instance = (Task) type.newInstance();
            String page = instance.execute(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } catch (
                ClassNotFoundException |
                InstantiationException | 
                IllegalAccessException error) {
            throw new ServletException(error);
        }
    }
    
    private String getClassPath(HttpServletRequest request) {
        String task = request.getParameter("task");
        
        if (task == null)
            task = "Index";
        task = String.format("com.app.controller.%s", task);
        System.out.println(task);
        return task;
    }
}
