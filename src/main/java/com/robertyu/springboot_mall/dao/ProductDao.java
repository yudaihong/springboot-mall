package com.robertyu.springboot_mall.dao;

import com.robertyu.springboot_mall.model.Product;

public interface ProductDao {

    //查詢商品，透過ProductId
    Product getProductById(Integer productId);
}
