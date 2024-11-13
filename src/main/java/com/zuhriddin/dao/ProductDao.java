package com.zuhriddin.dao;

import com.zuhriddin.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProductDao extends DatabaseConnection {
    private static final String GET_PRODUCTS = "select * from get_product()";
    private static final String ADD_PRODUCT = "select * from add_product(i_name := ?, i_price := ?, i_images := ?::json, i_params := ?::json, i_colours := ?::json, i_description := ?, i_discount := ?, i_from_delivery := ?, i_to_delivery := ?, i_created_by := ?, i_updated_by := ?)";
    private static final String DELETE_PRODUCT = "select * from delete_product(i_id := ?)";
    private static final String UPDATE_PRODUCT = "select * from update_product(i_id := ?, i_name := ?, i_price := ?, i_description := ?, i_discount := ?, i_from_delivery := ?, i_to_delivery := ?, i_updated_by := ?)";

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

    public Product addProduct(Product product) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getImages());
            preparedStatement.setString(4, product.getParams());
            preparedStatement.setString(5, product.getColours());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getDiscount());
            preparedStatement.setInt(8, product.getFromDelivery());
            preparedStatement.setInt(9, product.getToDelivery());
            preparedStatement.setString(10, product.getCreatedBy());
            preparedStatement.setString(11, product.getCreatedBy());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public void deleteProduct(int productId) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public Product updateProduct(Product product) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getDiscount());
            preparedStatement.setInt(6, product.getFromDelivery());
            preparedStatement.setInt(7, product.getToDelivery());
            preparedStatement.setString(8, product.getUpdatedBy());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
