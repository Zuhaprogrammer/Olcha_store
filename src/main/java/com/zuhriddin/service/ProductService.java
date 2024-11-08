package com.zuhriddin.service;

import com.zuhriddin.dao.ProductDao;
import com.zuhriddin.model.Product;
import lombok.RequiredArgsConstructor;
import java.util.*;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public List<Product> listProducts() {
        return productDao.getProducts();
    }
}
