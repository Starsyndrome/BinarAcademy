package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    void addNewOrder(Order order); // auto generate id
    void addNewOrderDetail(OrderDetail orderDetail); // auto generate id
    List<OrderResponse> getAllOrder();
}