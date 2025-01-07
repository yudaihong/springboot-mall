package com.robertyu.springboot_mall.controller;

import com.robertyu.springboot_mall.constant.ProductCategory;
import com.robertyu.springboot_mall.dto.ProductQueryParams;
import com.robertyu.springboot_mall.dto.ProductRequest;
import com.robertyu.springboot_mall.model.Product;
import com.robertyu.springboot_mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy ,
            @RequestParam(defaultValue = "desc") String sort
            ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);

        List<Product>productList = productService.getProducts(productQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

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

     @DeleteMapping("/products/{productId}")
     public ResponseEntity<?>deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

     }
}
