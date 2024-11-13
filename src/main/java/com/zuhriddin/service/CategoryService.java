package com.zuhriddin.service;

import com.zuhriddin.dao.CategoryDao;
import com.zuhriddin.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public List<Category> listCategory() {
        return categoryDao.getCategories();
    }

    public Category addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    public void deleteCategory(int categoryId) {
        categoryDao.deleteCategory(categoryId);
    }

    public Category updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }
}
