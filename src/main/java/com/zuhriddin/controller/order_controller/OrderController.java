package com.zuhriddin.controller.order_controller;

import com.zuhriddin.dao.OrderDao;
import com.zuhriddin.model.Order;
import com.zuhriddin.service.OrderService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/admin/order-list")
public class OrderController extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.orderService = new OrderService(new OrderDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.listOrders();
        req.setAttribute("orderList", orderList);
        String[] userPrivilegesFromCookie = (String[]) req.getAttribute("auth_privileges");
        if (userPrivilegesFromCookie != null) {
            List<String> privileges = Arrays.asList(userPrivilegesFromCookie);
            req.setAttribute("privileges", privileges);
        }
        req.getRequestDispatcher("order-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
