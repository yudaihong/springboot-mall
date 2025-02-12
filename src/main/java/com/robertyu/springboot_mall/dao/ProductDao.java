package com.robertyu.springboot_mall.dao;

import com.robertyu.springboot_mall.dto.ProductQueryParams;
import com.robertyu.springboot_mall.dto.ProductRequest;
import com.robertyu.springboot_mall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);
    //查詢商品，透過ProductId
    Product getProductById(Integer productId);
    //新增商品
    Integer createProduct(ProductRequest productRequest);
    //更新商品資訊
    void updateProduct(Integer productId , ProductRequest productRequest );
    //更新庫存
    void updateStock(Integer productId , Integer stock);
    //刪除商品
    void deleteProductById(Integer productId);

}
