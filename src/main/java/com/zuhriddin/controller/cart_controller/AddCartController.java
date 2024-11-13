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

@WebServlet("/admin/add-cart")
public class AddCartController extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.cartService = new CartService(new CartDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String productId = req.getParameter("productId");
        String quantity = req.getParameter("quantity");

        Cart cart = new Cart(Integer.parseInt(userId), Integer.parseInt(productId), Integer.parseInt(quantity));
        cartService.addCart(cart);
        resp.sendRedirect("/admin/cart-list");
    }
}
