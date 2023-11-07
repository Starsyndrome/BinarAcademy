package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.OrderDetailResponse;
import org.binaracademy.challenge4.DTO.response.OrderResponse;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    void addNewOrder(){
        Order order = Order.builder()
                .users(Users.builder()
                        .id(1L)
                        .build())
                .orderTime(LocalDateTime.now())
                .destinationAddress("Jakarta")
                .completed(true)
                .orderDetails(Arrays.asList(OrderDetail.builder()
                                .product(Product.builder()
                                        .productID("productidtest")
                                        .build())
                                .qty(1)
//                                .totalPrice() auto
                        .build()))
                .build();

        ResponseEntity<String> addNewOrder = orderController.addNewOrder(order);
        Mockito.when(orderService.addNewOrder(order)).thenReturn(order);
        Mockito.verify(orderService, Mockito.times(1)).addNewOrder(order);

        Assertions.assertEquals(HttpStatus.OK, addNewOrder.getStatusCode());
        Assertions.assertNotNull(addNewOrder);
    }

    @Test
    void getAllOrder(){
        Mockito.when(orderService.getAllOrder()).thenReturn(Arrays.asList(OrderDetailResponse.builder()
                        .username("OrderTest")
                        .orderResponse(OrderResponse.builder()
                                .orderTime(LocalDateTime.now())
                                .destinationAddress("Setiabudi, Jakarta Selatan")
                                .completed(true)
                                .build())
                        .productName("Test Order")
                        .productPrice(10000.0)
                        .quantity(1)
                        .totalPrice(10000.0)
                .build()));

        ResponseEntity<List<OrderDetailResponse>> getAllOrderDetail = orderController.getAllOrderDetail();
        Mockito.verify(orderService, Mockito.times(1)).getAllOrder();

        Assertions.assertEquals(HttpStatus.OK, getAllOrderDetail.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(getAllOrderDetail.getBody()).size());
        Assertions.assertNotNull(getAllOrderDetail);
    }
}