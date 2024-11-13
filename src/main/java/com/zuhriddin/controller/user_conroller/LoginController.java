package com.zuhriddin.controller.user_conroller;

import com.zuhriddin.dao.UserDao;
import com.zuhriddin.model.User;
import com.zuhriddin.model.enumaration.UserRole;
import com.zuhriddin.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User login = userService.login(email, password);
        addUsernameToCookie(resp, email, login.getPrivileges());

        if (login.getRole().equals(UserRole.ADMIN)) {
            resp.sendRedirect("/admin/category-list");
        } else {
            resp.getWriter().print("This user is just a USER");
        }
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
