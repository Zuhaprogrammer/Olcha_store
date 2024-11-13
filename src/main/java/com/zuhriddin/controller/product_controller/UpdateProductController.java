package com.zuhriddin.controller.product_controller;

import com.sun.net.httpserver.HttpServer;
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

@WebServlet("/admin/update-product")
public class UpdateProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.productService = new ProductService(new ProductDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String discount = req.getParameter("discount");
        String fromDelivery = req.getParameter("fromDelivery");
        String toDelivery = req.getParameter("toDelivery");
        String email = (String) req.getAttribute("authentication");

        Product product = new Product(Integer.parseInt(id), name, Integer.parseInt(price), description,
                Integer.parseInt(discount), Integer.parseInt(fromDelivery), Integer.parseInt(toDelivery), email);
        productService.updateProduct(product);
        resp.sendRedirect("/admin/product-list");
    }
}
