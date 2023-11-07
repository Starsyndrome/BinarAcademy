package org.binaracademy.challenge4.service.spy;

import org.binaracademy.challenge4.DTO.response.OrderDetailResponse;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.Users;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.service.implement.OrderServiceImplements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTestSpy {

    @Autowired
    OrderServiceImplements orderService;

    @SpyBean
    OrderDetailRepository orderDetailRepository;

    @Test
    void getAllOrder(){
        // Test passed, tapi entah pas di-test gini ga ke-insert di table order detail, cuma ke-insert di table order
        orderService.addNewOrder(Order.builder()
                        .users(Users.builder()
                                .id(2L)
                                .build())
                        .orderTime(LocalDateTime.now())
                        .destinationAddress("Bojong")
                        .completed(true)
                        .orderDetails(Arrays.asList(OrderDetail.builder()
                                        .product(Product.builder()
                                                .productID("ba9356c3-4bd2-491e-b845-f1eda59508c2")
                                                .build())
                                        .qty(1)
                                .build()))
                .build());

        Mockito.when(orderDetailRepository.findAll()).thenReturn(Arrays.asList(OrderDetail.builder()
                        .order(Order.builder()
                                .users(Users.builder()
                                        .username("Alfarizki")
                                        .build())
                                .orderTime(LocalDateTime.now())
                                .destinationAddress("Jakarta")
                                .completed(true)
                                .build())
                        .product(Product.builder()
                                .productID("ba9356c3-4bd2-491e-b845-f1eda59508c2")
                                .build())
                        .qty(1)
                        .totalPrice(15000.0)
                .build()));

        List<OrderDetailResponse> responses = orderService.getAllOrder();
        Mockito.verify(orderDetailRepository, Mockito.times(1)).findAll();

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(1, responses.size());
    }
}