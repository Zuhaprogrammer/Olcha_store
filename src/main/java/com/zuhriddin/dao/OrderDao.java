package com.zuhriddin.dao;

import com.zuhriddin.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class OrderDao extends DatabaseConnection{
    private static final String GET_ORDERS = "select * from get_orders()";

    public List<Order> getOrders() {
        try (Connection connection = connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order(resultSet);
                orderList.add(order);
            }
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Connection is not exist.");
        }
    }
}
