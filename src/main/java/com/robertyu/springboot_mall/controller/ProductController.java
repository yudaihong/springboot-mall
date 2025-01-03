package com.robertyu.springboot_mall.controller;

import com.robertyu.springboot_mall.dto.ProductRequest;
import com.robertyu.springboot_mall.model.Product;
import com.robertyu.springboot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product>getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /*
    createdProduct 註解 @Valid 需要加上才會使 dto.ProductRequest @NotNull註解 生效
    API_FLOW
    Step1.create
    Step2.afterCreate check
    Step3.return Https.Status.Create
     */
    @PostMapping("/products")
    public ResponseEntity<Product>creatProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
     public ResponseEntity<Product>updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //檢查 product 是否存在
        Product product = productService.getProductById(productId);
        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);
        return  ResponseEntity.status(HttpStatus.OK).body(updateProduct);
     }
}
