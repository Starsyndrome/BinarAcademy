package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.OrderDetail;


public interface OrderService {
    Boolean newOrder(OrderDetail orderDetail);
}
