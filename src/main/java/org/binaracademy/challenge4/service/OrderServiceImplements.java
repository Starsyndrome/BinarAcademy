package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class OrderServiceImplements implements OrderService{

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public OrderResponse newOrder(String name) {
        Page<Product> products = productRepository.getAllProduct(PageRequest.of(2,2));
        Product product = products.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setProduct(Product.builder()
                        .price(20000)
                .build());
        orderDetail.setQty(2);
        orderDetail.setTotalPrice(40000);
        orderDetailRepository.save(orderDetail);

        return OrderResponse.builder()
                .productName(orderDetail.getProduct().getProductName())
                .productPrice(orderDetail.getProduct().getPrice())
                .totalPrice(orderDetail.getTotalPrice())
                .build();
    }
}