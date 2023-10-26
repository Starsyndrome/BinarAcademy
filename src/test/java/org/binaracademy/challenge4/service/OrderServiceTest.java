package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;
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
        orderService.addNewOrder(OrderDetail.builder()
                        .product(Product.builder()
                                .productID("91e72758-28f1-4f7a-8246-23174ef093d3")
                                .build())
                        .qty(2)
                        .totalPrice(34000.0)
                        .order(Order.builder()
                                .users(Users.builder()
                                        .username("Alfarizki")
                                        .build())
                                .orderTime(new Date())
                                .destinationAddress("Kramat Lontar")
                                .completed(true)
                                .build())
                .build());
    }

    @Test
    void getAllOrder(){
        List<OrderDetailResponse> orderResponses = orderService.getAllOrder();
        Assertions.assertEquals(8, orderResponses.size());
    }
}