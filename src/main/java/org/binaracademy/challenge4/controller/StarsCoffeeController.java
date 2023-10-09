 package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
@Component
public class StarsCoffeeController {
    /* Maaf ya kak udah ngaco banget, saya ga bisa ngikutin materi sebanyak ini dengan cepat.
    Susah banget rasanya, mau nyerah juga udah nanggung ga bisa, saya pengennya bener-bener
    review materi ini lagi, implementasinya susah betul, kayak otak saya ga sampe gitu kak ya Allah wkwk
     */
    @PostConstruct
    public void init() throws ParseException{
        try {
            this.mainMenu();
        }catch (InputMismatchException ime){
            System.out.println("Input harus berupa angka!");
        }
    }

    private Scanner scanner = new Scanner(System.in);
    @Autowired
    MerchantController merchantController;
    @Autowired
    OrderController orderController;
    @Autowired
    ProductController productController;
    @Autowired
    UserController userController;

    public void mainMenu() throws InputMismatchException, ParseException {
        log.info("Processing mainMenu()");
        System.out.println("======================================");
        System.out.println("\tSelamat datang di Stars Coffee");
        System.out.println("======================================");
        System.out.println("Silakan pilih menu yang tersedia:");
        System.out.println("1. Lihat produk yang tersedia");
        System.out.println("2. Lihat merchant yang tersedia");
        System.out.println("3. Tambahkan produk");
        System.out.println("4. Tambahkan merchant");
        System.out.println("5. Tambahkan user");
        System.out.println("6. Order");
        System.out.println("0. Keluar");
        System.out.print("Inputkan pilihan => ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        switch (pilihan){
            case 1:
                try {
                    productController.productCurrentlyAvailable(null);
                    this.mainMenu();
                    break;
                }catch (InputMismatchException | IllegalArgumentException ime){
                    this.mainMenu();
                }
                break;
            case 2:
                try {
                    merchantController.merchantCurrentlyAvailable(null);
                    this.mainMenu();
                    break;
                }catch (InputMismatchException | IllegalArgumentException ime){
                    this.mainMenu();
                }
            case 3:
                productController.addNewProduct();
                this.mainMenu();
                break;
            case 4:
                merchantController.addNewMerchant();
                this.mainMenu();
                break;
            case 5:
                userController.addUser();
                this.mainMenu();
            case 6:
                orderController.newOrder();
                this.mainMenu();
                break;
            case 0:
                System.out.println("Terima kasih sudah berkunjung!");
                System.exit(0);
                break;
            default:
                System.out.println("Nomor yang Anda inputkan tidak tersedia!");
                this.mainMenu();
                break;
        }
    }
}