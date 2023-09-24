package org.binaracademy.controller;

import org.binaracademy.service.OrderService;
import org.binaracademy.service.OrderServiceImplement;

public class MenuController {
    OrderService orderService;

    public MenuController(){
        orderService = new OrderServiceImplement();
    }

    public void menuStart(){
        orderService.menuMakananPemesanan();
        orderService.menuPembayaran();
        orderService.cetakStruk("C:/BEJIntelliJEdu/Challenge3/src/main/resources/struk.txt");
    }
}
