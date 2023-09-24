package org.binaracademy.controller;

import org.binaracademy.service.OrderService;
import org.binaracademy.service.OrderServiceImplement;

public class MenuController {
    private OrderService orderService;

    public MenuController(){
        orderService = new OrderServiceImplement();
    }

    public void menuStart(){
        orderService.menuPemesanan();
        orderService.menuPembayaran();
        orderService.cetakStrukPembayaran();
    }
}
