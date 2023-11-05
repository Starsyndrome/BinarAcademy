package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.DTO.response.OrderDetailResponse;
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
    public ResponseEntity<String> addNewOrder(@RequestBody Order order){
        orderService.addNewOrder(order);
        return ResponseEntity.ok()
                .body("Order successful! Enjoy the food!");
    }

    @GetMapping(value = "/getAllOrder", produces = "application/json")
    public ResponseEntity<List<OrderDetailResponse>> getAllOrderDetail(){
        return ResponseEntity.ok()
                .body(orderService.getAllOrder());
    }
}