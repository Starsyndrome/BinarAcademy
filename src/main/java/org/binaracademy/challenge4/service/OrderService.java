package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.response.OrderResponse;


public interface OrderService {
    OrderResponse newOrder(String name);
}
