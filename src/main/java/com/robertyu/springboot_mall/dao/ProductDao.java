package com.robertyu.springboot_mall.dao;

import com.robertyu.springboot_mall.dto.ProductRequest;
import com.robertyu.springboot_mall.model.Product;

public interface ProductDao {

    //查詢商品，透過ProductId
    Product getProductById(Integer productId);
    //新增商品
    Integer createProduct(ProductRequest productRequest);
    //更新商品資訊
    void updateProduct(Integer productId , ProductRequest productRequest );
    //刪除商品
    void deleteProductById(Integer productId);
}
