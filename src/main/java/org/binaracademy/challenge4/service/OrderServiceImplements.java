 package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImplements implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void addNewOrder(OrderDetail orderDetail) {
        log.info("Processing order");
        Optional.ofNullable(orderDetail)
                .map(orders -> orderDetailRepository.save(orderDetail))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Order success with username {}", orderDetail.getOrder().getUsers().getUsername());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Error");
                    return Boolean.FALSE;
                });
        log.info("Thanks for order at Stars Coffee!");
    }

    @Override
    public List<OrderDetailResponse> getAllOrder() {
        log.info("Success get all order!");
        return orderDetailRepository.findAll().stream()
                .map(orders -> OrderDetailResponse.builder()
                        .username(orders.getOrder().getUsers().getUsername())
                        .orderResponse(OrderResponse.builder()
                                .orderTime(orders.getOrder().getOrderTime())
                                .destinationAddress(orders.getOrder().getDestinationAddress())
                                .complete(orders.getOrder().getCompleted())
                                .build())
                        .productName(orders.getProduct().getProductName())
                        .productPrice(orders.getProduct().getPrice())
                        .quantity(orders.getQty())
                        .totalPrice(orders.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}