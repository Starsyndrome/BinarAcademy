package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
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
    public void addNewOrder(Order order) { // id auto generate
        Optional.ofNullable(order)
                .map(orders -> orderRepository.save(order))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully order with order ID: {}", order.getOrderID());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Error");
                    return Boolean.FALSE;
                });
    }

    @Override
    public void addNewOrderDetail(OrderDetail orderDetail) {
        Optional.ofNullable(orderDetail)
                .map(orders -> orderDetailRepository.save(orderDetail))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully order, thanks!");
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Error");
                    return Boolean.FALSE;
                });
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        log.info("Success get all order!");
        return orderDetailRepository.findAll().stream()
                .map(orders -> OrderResponse.builder()
                        .productName(orders.getProduct().getProductName())
                        .productPrice(orders.getProduct().getPrice())
                        .quantity(orders.getQty())
                        .totalPrice(orders.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}