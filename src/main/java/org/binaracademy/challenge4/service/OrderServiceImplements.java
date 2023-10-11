package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImplements implements OrderService{

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Boolean newOrder(OrderDetail orderDetail) {
       return Optional.ofNullable(orderDetail)
               .map(orderResponse1 -> orderDetailRepository.save(orderDetail))
               .map(result -> {
                   boolean isSuccess = true;
                   log.info("Berhasil memesan dan masuk ke database");
                   return isSuccess;
               })
               .orElseGet(() -> {
                   log.info("Pemesanan makanan gagal dan data tidak terinput ke database");
                   return Boolean.FALSE;
               });
    }
}