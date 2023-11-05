package org.binaracademy.challenge4.service;
;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.DTO.response.OrderDetailResponse;

import java.util.List;

public interface OrderService {

    Order addNewOrder(Order order);

    List<OrderDetailResponse> getAllOrder();
}