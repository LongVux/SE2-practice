package com.controller;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class Homepage_controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // initiate session
        HttpSession session = request.getSession();

        if(!session.isNew()){
            System.out.println("You already have a session!");
            System.out.println(session.getAttribute("role"));
        }
        RequestDispatcher Homepage_view = request.getRequestDispatcher(("/view/Homepage_view.jsp"));
        Homepage_view.forward(request, response);
    }

    public void destroy() {
    }
}