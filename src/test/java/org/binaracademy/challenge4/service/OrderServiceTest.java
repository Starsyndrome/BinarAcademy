package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Test
    void newOrder_Test(){
        orderService.addNewOrder(Order.builder()
                .users(Users.builder()
                        .userID("7d912a1d-b974-4526-8a1b-0bd959585922")
                        .build())
                .orderTime(new Date())
                .destinationAddress("Bogor")
                .completed(true)
                .build());
    }

    @Test
    void newOrderDetail_Test(){
        orderService.addNewOrderDetail(OrderDetail.builder()
                        .product(Product.builder()
                                .productID("91e72758-28f1-4f7a-8246-23174ef093d3")
                                .build())
                        .order(Order.builder()
                                .orderID("633ffc95-bf0e-496a-857e-d216024a6aa1")
                                .build())
                        .qty(1)
                        .totalPrice(17000.0)
                .build());
    }

    @Test
    void getAllOrder(){
        List<OrderResponse> orderResponses = orderService.getAllOrder();
        Assertions.assertEquals(2, orderResponses.size());
    }
}