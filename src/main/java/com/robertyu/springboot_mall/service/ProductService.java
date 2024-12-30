package com.robertyu.springboot_mall.service;

import com.robertyu.springboot_mall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}
