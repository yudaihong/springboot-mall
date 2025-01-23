package com.robertyu.springboot_mall.dao;

import com.robertyu.springboot_mall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId , Integer totalAmount);

    void createOrderItems(Integer orderId , List<OrderItem>orderItemList);
}
