package com.zuhriddin.service;

import com.zuhriddin.dao.CartDao;
import com.zuhriddin.model.Cart;
import lombok.RequiredArgsConstructor;
import java.util.*;

@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;

    public List<Cart> listCarts() {
        return cartDao.getCarts();
    }
}
