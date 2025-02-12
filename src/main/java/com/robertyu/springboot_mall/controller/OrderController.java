package com.robertyu.springboot_mall.controller;

import com.robertyu.springboot_mall.dto.CreateOrderRequest;
import com.robertyu.springboot_mall.model.Order;
import com.robertyu.springboot_mall.model.OrderItem;
import com.robertyu.springboot_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId ,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
       Integer orderId =  orderService.createOrder(userId , createOrderRequest);

        Order order = orderService.getOrderById(orderId);
       return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}