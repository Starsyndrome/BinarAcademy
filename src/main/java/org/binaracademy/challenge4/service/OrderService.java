package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.OrderDetail;

// Terutama untuk logic order, kayak belum masuk aja gitu tiba-tiba ngerjain logic order yang complex
public interface OrderService {
    Boolean newOrder(OrderDetail orderDetail);
}
