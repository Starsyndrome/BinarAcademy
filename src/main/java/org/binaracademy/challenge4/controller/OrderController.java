package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetails;
import org.binaracademy.challenge4.model.response.OrderDetailResponse;
import org.binaracademy.challenge4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping(value = "/newOrder", consumes = "application/json")
    public ResponseEntity addNewOrder(@RequestBody OrderDetails orderDetails){
        orderService.addNewOrder(orderDetails);
        return ResponseEntity.ok("Successfully order with username "+ orderDetails.getOrder()
                .getUsers().getUsername() + "!");
    }

    @GetMapping(value = "/getAllOrderDetail", produces = "application/json")
    public List<OrderDetailResponse> getAllOrderDetail(){
        return orderService.getAllOrder();
    }
}