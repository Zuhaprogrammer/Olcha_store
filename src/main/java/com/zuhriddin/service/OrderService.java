package com.zuhriddin.service;

import com.zuhriddin.dao.OrderDao;
import com.zuhriddin.model.Order;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;

    public List<Order> listOrders () {
        return orderDao.getOrders();
    }
}
