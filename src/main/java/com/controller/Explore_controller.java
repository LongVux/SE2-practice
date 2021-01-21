package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.String;

@WebServlet(name="explore", value="/explore")
public class Explore_controller extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // checking session
        HttpSession session = request.getSession();

        synchronized (session){
            String name = (String) session.getAttribute("role");
            request.setAttribute("role", name);
            RequestDispatcher Explore_view = request.getRequestDispatcher(("/view/Explore_view.jsp"));
            Explore_view.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // checking with database process
        System.out.println("checking");


        // redirect to the current page
        response.sendRedirect(request.getParameter("from"));

    }

    public void destroy() {
    }
}

