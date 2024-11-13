package com.zuhriddin.controller.category_controller;

import com.zuhriddin.dao.CategoryDao;
import com.zuhriddin.model.Category;
import com.zuhriddin.service.CategoryService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/admin/category-list")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.categoryService = new CategoryService(new CategoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.listCategory();
        req.setAttribute("categoryList", categoryList);
        String[] userPrivilegesFromCookie = (String[]) req.getAttribute("auth_privileges");
        if (userPrivilegesFromCookie != null) {
            List<String> privileges = Arrays.asList(userPrivilegesFromCookie);
            req.setAttribute("privileges", privileges);
        }
        req.getRequestDispatcher("category-list.jsp").forward(req, resp);
    }
}
