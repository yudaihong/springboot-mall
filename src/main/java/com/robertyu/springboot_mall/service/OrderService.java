package com.robertyu.springboot_mall.service;

import com.robertyu.springboot_mall.dto.CreateOrderRequest;
import com.robertyu.springboot_mall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId , CreateOrderRequest createOrderRequest);
}
