package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetails;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;

import java.util.List;

public interface OrderService {
    void addNewOrder(Order order); // auto generate id
    void addNewOrderDetail(OrderDetails orderDetails); // auto generate id
    List<OrderDetailResponse> getAllOrder();
}