package com.zuhriddin.dao;

import com.zuhriddin.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CategoryDao extends DatabaseConnection{
    private static final String GET_CATEGORIES = "select * from get_category()";
    private static final String ADD_CATEGORY = "select * from add_category(i_name := ?, i_parent_id := ?, i_created_by := ?, i_updated_by := ?)";
    private static final String DELETE_CATEGORY = "select * from delete_category(i_id := ?)";
    private static final String UPDATE_CATEGORY = "select * from update_category(i_id := ?, i_name := ?, i_updated_by := ?, i_parent_id := ?)";
    private static final String UPDATE_CATEGORY_WITHOUT_PARENT_ID = "select * from update_category(i_id := ?, i_name := ?, i_updated_by := ?)";

    public List<Category> getCategories() {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categoryList = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(resultSet);
                categoryList.add(category);
            }
            return categoryList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public Category addCategory(Category category) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getParentId());
            preparedStatement.setString(3, category.getCreatedBy());
            preparedStatement.setString(4, category.getCreatedBy());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Category(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public void deleteCategory(int categoryID) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)) {
            preparedStatement.setInt(1, categoryID);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public Category updateCategory(Category category) {
        String BASE_QUERY;
        if (category.getParentId() == 0) {
            BASE_QUERY = UPDATE_CATEGORY_WITHOUT_PARENT_ID;
        } else {
            BASE_QUERY = UPDATE_CATEGORY;
        }
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(BASE_QUERY)) {
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getUpdatedBy());
            if (category.getParentId() != 0) {
                preparedStatement.setInt(4, category.getParentId());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet);
            return new Category(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
