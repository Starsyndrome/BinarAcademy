package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.DTO.response.OrderDetailResponse;
import org.binaracademy.challenge4.DTO.response.OrderResponse;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImplements implements OrderService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order addNewOrder(Order order) {
        log.info("Processing new order");
        try {
            if (order.getUsers() == null) {
                throw new NullPointerException("User not found!");
            }
            Order orders = orderRepository.save(order);
            List<OrderDetail> orderDetailsList = orders.getOrderDetails();
            for (OrderDetail orderDetail : orderDetailsList) {
                Product product = productRepository.getById(orderDetail.getProduct().getProductID());
                orderDetail.setOrder(orders);
                orderDetail.setProduct(product);
                orderDetail.setQty(orderDetail.getQty());
                orderDetail.setTotalPrice(orderDetail.getProduct().getPrice() * orderDetail.getQty());
                orderDetailRepository.save(orderDetail);
                log.info("Order success with ID: {}", orders.getUsers().getId());
            }
        } catch (Exception e){
            log.error("Order failed with ID: {}", order.getUsers().getId());
        }
        return order;
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
                                .completed(orders.getOrder().getCompleted())
                                .build())
                        .productName(orders.getProduct().getProductName())
                        .productPrice(orders.getProduct().getPrice())
                        .quantity(orders.getQty())
                        .totalPrice(orders.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}