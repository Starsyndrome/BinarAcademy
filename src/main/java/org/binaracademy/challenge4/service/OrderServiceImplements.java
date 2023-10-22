 package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetails;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.model.response.UserResponseOrder;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /*
    Bingung logic ordernya asli, tapi yang saya buat ini bisa sih, tapi dua kali insert,
    dapetin order_id di method addNewOrder, lalu order_id-nya dibawa ke method getAllOrder.
    Hard code gitu perhitungan totalPricenya, langsung aja dijumlahin sendiri wkwk.
     */
    @Override
    public void addNewOrder(Order order) { // id auto generate
        log.info("Process add new order");
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
        log.info("Successfully add new order!");
    }

    @Override
    public void addNewOrderDetail(OrderDetails orderDetails) {
        log.info("Process order");
        Optional.ofNullable(orderDetails)
                .map(orders -> orderDetailRepository.save(orderDetails))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully order, thanks!");
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
                        .userResponseOrder(UserResponseOrder.builder()
                                .username(orders.getOrder().getUsers().getUsername())
                                .build())
                        .orderResponse(OrderResponse.builder()
                                .orderTime(new Date()) // Ini bingung deh kak formatnya buat time :(
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