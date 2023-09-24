package org.binaracademy.service;

import org.binaracademy.model.ProductCart;
import org.binaracademy.model.ProductMenu;
import org.binaracademy.repository.ProductRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImplement implements OrderService {
    private List<ProductMenu> menuList;
    private List<ProductCart> productCartList;
    private Scanner MBA;
    int pilihanCust, jumlahPesanan = 0;
    double totalPesanan = 0;

    public OrderServiceImplement(){
        menuList = new ArrayList<>();
        productCartList = new ArrayList<>();
        MBA = new Scanner(System.in);
        menuList = ProductRepository.menuList();
    }

    @Override
    public void menuPemesanan() {
        try {
            do {
                System.out.println("*******************************");
                System.out.println("Selamat datang di Stars Coffee!");
                System.out.println("*******************************");
                System.out.println("Silakan pilih menu yang ingin dipesan:");
                for (ProductMenu productMenu : menuList) {
                    System.out.println(productMenu.getNomorMenu() + ". " + productMenu.getNamaMenu() + "\t | " + productMenu.getHargaMenu());
                }
                System.out.println("99. Pesan dan Bayar");
                System.out.println("0. Keluar Aplikasi");
                System.out.println();
                System.out.print("Inputkan nomor yang tersedia pada menu: ");
                pilihanCust = MBA.nextInt();

                if (pilihanCust >= 1 && pilihanCust <= menuList.size()) {
                    System.out.print("Inputkan jumlahnya: ");
                    jumlahPesanan = MBA.nextInt();
                }
                System.out.println();
                switch (pilihanCust) {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        ProductMenu productMenu = menuList.get(0);
                        ProductCart productCart = new ProductCart(productMenu, jumlahPesanan);
                        productCartList.add(productCart);
                        break;
                    case 2:
                        productMenu = menuList.get(1);
                        productCart = new ProductCart(productMenu, jumlahPesanan);
                        productCartList.add(productCart);
                        break;
                    case 3:
                        productMenu = menuList.get(2);
                        productCart = new ProductCart(productMenu, jumlahPesanan);
                        productCartList.add(productCart);
                        break;
                    case 4:
                        productMenu = menuList.get(3);
                        productCart = new ProductCart(productMenu, jumlahPesanan);
                        productCartList.add(productCart);
                        break;
                    case 5:
                        productMenu = menuList.get(4);
                        productCart = new ProductCart(productMenu, jumlahPesanan);
                        productCartList.add(productCart);
                        break;
                    case 99:
                        break;
                    default:
                        System.out.println("Inputkan angka sesuai yang ada di menu!");
                        break;
                }
            } while (pilihanCust != 99);
        }catch (InputMismatchException ime){
            System.out.println("Input harus berupa angka!");
        }
    }

    @Override
    public void menuPembayaran() {
        System.out.println("========================================");
        System.out.println("\t\t\t  Pembayaran\t\t\t");
        System.out.println("========================================");
        System.out.println("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah");
        for (ProductCart item : productCartList) {
            System.out.println(item.getProduct().getNomorMenu() + "\t | " + item.getProduct().getNamaMenu() + "\t | " + item.getProduct().getHargaMenu() + "\t | " + item.getQty());
            totalPesanan += item.getTotalHarga();
        }
        System.out.println();
        System.out.println("Totalnya yaitu: " + totalPesanan);
    }

    @Override
    public void cetakStrukPembayaran() {
        try {
            totalPesanan = 0;
            File file = new File("C:/BEJIntelliJEdu/Challenge2/src/main/resources/struk.txt");
            if (file.createNewFile()){
                System.out.println("File berhasil dibuat");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\t     Struk Pembayaran\t\n");
            bw.newLine();
            bw.write("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah\n");
            for (ProductCart item : productCartList) {
                bw.write(item.getProduct().getNomorMenu() + "\t | " + item.getProduct().getNamaMenu() +
                        "\t | " + item.getProduct().getHargaMenu() + "\t | " + item.getQty() + "\n");
                totalPesanan += item.getTotalHarga();
            }
            bw.newLine();
            bw.newLine();
            bw.write("Totalnya yaitu: " + totalPesanan);
            bw.newLine();
            bw.write("Terima kasih sudah datang ke Stars Coffee!");
            bw.flush();
            bw.close();
            System.out.println();
            System.out.println("Terima kasih sudah datang ke Stars Coffee!");
        } catch (IOException e){
            System.out.println("File gagal dibuat");
        }
    }
}