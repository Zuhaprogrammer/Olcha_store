package com.zuhriddin.dao;

import com.zuhriddin.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CartDao extends DatabaseConnection {
    private static final String GET_CARTS = "select * from get_cart()";

    public List<Cart> getCarts() {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CARTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cart> cartList = new ArrayList<>();
            while (resultSet.next()) {
                Cart cart = new Cart(resultSet);
                cartList.add(cart);
            }
            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
