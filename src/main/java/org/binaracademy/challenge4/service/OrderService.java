package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;

import java.util.List;

public interface OrderService {
    void addNewOrder(OrderDetail orderDetail); // auto generate id

    List<OrderDetailResponse> getAllOrder();
}