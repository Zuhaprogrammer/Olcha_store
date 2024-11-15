package com.zuhriddin.controller.cart_controller;

import com.zuhriddin.dao.CartDao;
import com.zuhriddin.model.Cart;
import com.zuhriddin.service.CartService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/admin/cart-list")
public class CartController extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.cartService = new CartService(new CartDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cart> cartList = cartService.listCarts();
        req.setAttribute("cartList", cartList);
        String[] userPrivilegesFromCookie = (String[]) req.getAttribute("auth_privileges");
        if (userPrivilegesFromCookie != null) {
            List<String> privileges = Arrays.asList(userPrivilegesFromCookie);
            req.setAttribute("privileges", privileges);
        }
        req.getRequestDispatcher("cart-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
