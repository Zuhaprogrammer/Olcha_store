package com.zuhriddin.dao;

import com.zuhriddin.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CartDao extends DatabaseConnection {
    private static final String GET_CARTS = "select * from get_cart()";
    private static final String ADD_CART = "select * from add_cart(i_user_id := ?, i_product_id := ?, i_quantity := ?)";
    private static final String DELETE_CART = "select * from delete_cart(i_id := ?)";

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

    public Cart addCart(Cart cart) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CART)) {
            preparedStatement.setInt(1, cart.getUserId());
            preparedStatement.setInt(2, cart.getProductId());
            preparedStatement.setInt(3, cart.getQuantity());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Cart(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }

    public void deleteCart (int cartId) {
        try (Connection connection = connection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
