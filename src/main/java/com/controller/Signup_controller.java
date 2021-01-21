package com.controller;

import com.dao.User_dao;
import com.helper.FormValidationUtils;
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

@WebServlet(name="signup", value="/signup")
public class Signup_controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher Signup_view = request.getRequestDispatcher(("/view/Signup_view.jsp"));
        Signup_view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // checking with database process
        System.out.println("signing up");

        User_dao userDAO = new User_dao();

        String given_username = request.getParameter("username");
        String given_password = request.getParameter("password");
        String given_role = request.getParameter("role");

        System.out.println(given_password);

        String error = "";

        System.out.println(userDAO.getUser(given_username));
        if(userDAO.getUser(given_username).getUsername() != null){
            error += "the username is unavailable/n" ;
        }
        if(!FormValidationUtils.validateEmpty(given_username)){
            error += "must fill in username/n";
        }
        if(!FormValidationUtils.validateEmpty(given_password)){
            error += "must fill in password/n";
        }
        if(!FormValidationUtils.validateEmpty(given_role)) {
            error += "must choose the role/n";
        }

        // if there given data is correct
        if (error.equals("")){
            User user = new User();
            user.setUsername(given_username);

            String securedPassword = PasswordUtils.generateSecurePassword(given_password, PasswordUtils.getSalt(10));
            user.setPassword(securedPassword);
            user.setRole(given_role);

            if(userDAO.insertUser(user)){
                HttpSession session = request.getSession();

                synchronized (session){
                    session.setAttribute("role", user.getRole());
                    // redirect to the current page
                    response.sendRedirect(request.getParameter("from"));
                }
            } else {
                request.setAttribute("error", "database error, try again later");
                RequestDispatcher Signin_view = request.getRequestDispatcher(("/view/Signup_view.jsp"));
                Signin_view.forward(request, response);
            }
        } else {
            request.setAttribute("error", error);
            RequestDispatcher Signin_view = request.getRequestDispatcher(("/view/Signup_view.jsp"));
            Signin_view.forward(request, response);
        }
    }

    public void destroy() {
    }
}
