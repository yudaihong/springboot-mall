package com.robertyu.springboot_mall.service.impl;

import com.robertyu.springboot_mall.dao.OrderDao;
import com.robertyu.springboot_mall.dao.ProductDao;
import com.robertyu.springboot_mall.dto.BuyItem;
import com.robertyu.springboot_mall.dto.CreateOrderRequest;
import com.robertyu.springboot_mall.model.OrderItem;
import com.robertyu.springboot_mall.model.Product;
import com.robertyu.springboot_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        //創建訂單
        Integer orderId = orderDao.createOrder(userId , totalAmount);

        orderDao.createOrderItems(orderId , orderItemList);

        return orderId;
    }
}
