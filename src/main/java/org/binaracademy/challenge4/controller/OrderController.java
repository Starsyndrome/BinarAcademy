package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.OrderDetail;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@Component
public class OrderController {
    /*
    Puyeng di sini nih ya Allah mesennya gimana ini gagal terus
     */
    @Autowired
    OrderService orderService;
    private Scanner scanner = new Scanner(System.in);

    public void newOrder() throws ParseException {
        System.out.print("Jam pemesanan: ");
        String jamPesen = scanner.nextLine();
        System.out.print("Merchant Kode: ");
        String kodeMerchant = scanner.nextLine();
        System.out.print("Nama produk: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Kode Produk: ");
        String kodeProduk = scanner.nextLine();
        System.out.print("Harga produk: ");
        Integer hargaProduk = scanner.nextInt();
        System.out.print("Qty: ");
        Integer qty = scanner.nextInt();
        System.out.print("Total harga: ");
        Integer totalHarga = scanner.nextInt();

        OrderDetail orderDetail = OrderDetail.builder()
//                .orderDetailID("")
                .product(Product.builder()
                        .merchant(Merchant.builder()
//                                .open(true)
//                                .merchantLocation("")
//                                .merchantName("")
                                .merchantCode(kodeMerchant)
//                                .merchantID("")
                                .build())
                        .productName(namaProduk)
                        .price(hargaProduk)
//                        .productID("")
                        .productCode(kodeProduk)
                        .build())
                .order(Order.builder()
//                        .orderID("")
                        .orderTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(jamPesen))
//                        .users(null)
//                        .completed(true)
//                        .destinationAddress(null)
                        .build())
                .qty(qty)
                .totalPrice(totalHarga)
                .build();
        orderService.newOrder(orderDetail);
    }
}