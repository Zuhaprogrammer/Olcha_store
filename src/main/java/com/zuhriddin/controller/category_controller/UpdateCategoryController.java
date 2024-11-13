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

@WebServlet("/admin/update-category")
public class UpdateCategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.categoryService = new CategoryService(new CategoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String parentId = req.getParameter("parentId");
        String email = (String) req.getAttribute("authentication");

        Category category = new Category(Integer.parseInt(id), name, Integer.parseInt(parentId), email);
        categoryService.updateCategory(category);
        resp.sendRedirect("/admin/category-list");
    }
}
