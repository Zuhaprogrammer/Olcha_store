package com.zuhriddin.dao;

import com.zuhriddin.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProductDao extends DatabaseConnection {
    private static final String GET_PRODUCTS = "select * from get_product()";

    public List<Product> getProducts() {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
