package com.zuhriddin.controller.product_controller;

import com.zuhriddin.dao.ProductDao;
import com.zuhriddin.model.Product;
import com.zuhriddin.service.ProductService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/admin/product-list")
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.productService = new ProductService(new ProductDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.listProducts();
        req.setAttribute("productList", productList);
        String[] userPrivilegesFromCookie = (String[]) req.getAttribute("auth_privileges");
        if (userPrivilegesFromCookie != null) {
            List<String> privileges = Arrays.asList(userPrivilegesFromCookie);
            req.setAttribute("privileges", privileges);
        }

        req.getRequestDispatcher("product-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
