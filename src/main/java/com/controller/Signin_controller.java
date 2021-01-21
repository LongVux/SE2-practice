package com.controller;

import com.dao.User_dao;
import com.helper.PasswordUtils;
import com.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="signin", value="/signin")
public class Signin_controller extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher Signin_view = request.getRequestDispatcher(("/view/Signin_view.jsp"));
        Signin_view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // checking with database process
        System.out.println("checking");

        String given_username = request.getParameter("username");
        String given_password = request.getParameter("password");

        User_dao userDAO = new User_dao();
        User user = userDAO.getUser(given_username);

        System.out.println(user.getRole());

        // if there is a user
        if (user.getRole() != null){
            // checking the password
            // if the password is correct
            if (PasswordUtils.verifyUserPassword(given_password, user.getPassword())){
                HttpSession session = request.getSession();

                synchronized (session){
                    session.setAttribute("role", user.getRole());
                    // redirect to the current page
                    response.sendRedirect(request.getParameter("from"));
                }
            } else {
                // if there is no existed user or password is incorrect
                String error = "wrong password";
                request.setAttribute("error", error);
                RequestDispatcher Signin_view = request.getRequestDispatcher(("/view/Signin_view.jsp"));
                Signin_view.forward(request, response);
            }
        }else {
            // if there is no existed user or password is incorrect
            String error = "wrong username";
            request.setAttribute("error", error);
            RequestDispatcher Signin_view = request.getRequestDispatcher(("/view/Signin_view.jsp"));
            Signin_view.forward(request, response);
        }
    }

    public void destroy() {
    }
}
