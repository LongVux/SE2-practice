package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authen_filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;


        // checking the session
        HttpSession session = httpReq.getSession();
        System.out.println(session.getAttribute("role"));
        synchronized (session){
            if(!session.isNew()){
                if(session.getAttribute("role") == null || !session.getAttribute("role").equals("customer")){
                    HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
                    httpRes.sendRedirect("/signin?from=" + httpReq.getRequestURI());
                } else {
                    System.out.println("ok");
                    filterChain.doFilter(httpReq, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
