package com.robertyu.springboot_mall.service;

import com.robertyu.springboot_mall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
