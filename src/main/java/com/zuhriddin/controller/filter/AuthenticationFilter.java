package com.zuhriddin.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie[] cookies = request.getCookies();
        String email = null;
        String[] privileges = null;
        for (Cookie cookie : cookies) {
            if (email == null || privileges == null) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                }
                if (cookie.getName().equals("privileges")) {
                    privileges = cookie.getValue().split("/");
                }
            } else {
                break;
            }
        }
        if (email == null) {
            response.sendRedirect("/login");
            return;
        }
        addUsernameToCookie(response, email, privileges);
        request.setAttribute("authentication", email);
        request.setAttribute("auth_privileges", privileges);
        filterChain.doFilter(request, response);
    }

    protected void addUsernameToCookie(HttpServletResponse response, String email, String[] privileges) {
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(30);
        response.addCookie(cookie);
        if (privileges != null) {
            String joinedPrivileges = String.join("/", privileges);
            Cookie privilegesCookie = new Cookie("privileges", joinedPrivileges);
            privilegesCookie.setMaxAge(30);
            response.addCookie(privilegesCookie);
        }
    }
}
