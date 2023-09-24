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

public class OrderServiceImplement implements OrderService{
    private List<ProductMenu> menuList;
    private final List<ProductCart> productCartList;
    private final Scanner MBA;
    int pilihanCust;
    int jumlahPesanan = 0;
    double totalHarga;

    public OrderServiceImplement(){
        menuList = new ArrayList<>();
        menuList = ProductRepository.menuList();
        productCartList = new ArrayList<>();
        MBA = new Scanner(System.in);
    }
    @Override
    public void menuMakananPemesanan() {
        try {
            do {
                System.out.println("-----------------------------------------------");
                System.out.println("\t\tSelamat datang di Stars Coffee!");
                System.out.println("-----------------------------------------------");
                System.out.println("Silakan pilih menu yang ingin dipesan:");
                menuList.forEach(produkMenu -> System.out.println(produkMenu.getNomorMenu() + ". "
                        + produkMenu.getNamaMenu() + "\t | " + produkMenu.getHargaMenu()));
                System.out.println("99. Pesan dan Bayar");
                System.out.println("0. Keluar Aplikasi");
                System.out.println();
                System.out.print("Inputkan nomor yang tersedia pada menu: ");
                pilihanCust = MBA.nextInt();

                if (pilihanCust >= 1 && pilihanCust <= menuList.size()) {
                    System.out.print("Inputkan jumlahnya: ");
                    jumlahPesanan = MBA.nextInt();
                }
                switch (pilihanCust) {
                    case 0:
                        System.out.println("Terima kasih sudah datang ke Stars Coffee!");
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
        } catch (InputMismatchException ime) {
            System.out.println("Input harus berupa angka!");
        }
    }

    @Override
    public void menuPembayaran() {
        System.out.println("========================================");
        System.out.println("\t\t\t  Pembayaran\t\t\t");
        System.out.println("========================================");
        System.out.println("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah");
        totalHarga = productCartList.stream()
                .map(produkKeranjang -> {
                    System.out.println(produkKeranjang.getProduct().getNomorMenu() + "\t | "
                            + produkKeranjang.getProduct().getNamaMenu() + "\t | " + produkKeranjang
                            .getProduct().getHargaMenu() + "\t | " + produkKeranjang.getQty());
                    return produkKeranjang;
                })
                .reduce(0, (result, order) -> (int) (result + (order.getProduct().getHargaMenu() *
                        order.getQty())), Integer::sum);
        System.out.println();
        System.out.println("Totalnya yaitu: " + totalHarga);
    }

    @Override
    public String cetakStruk(String targetFile) {
        try {
            File file = new File(targetFile);
            if (file.createNewFile()) {
                System.out.println("File berhasil dibuat");
            }
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("\t\t\tStruk Pembayaran\t\n");
                bw.newLine();
                bw.write("No\t" + "\tPesanan" + "\t\t\tHarga" + "\t  Jumlah\n");
                totalHarga = productCartList.stream()
                        .map(produkKeranjang -> {
                            try {
                                bw.write(produkKeranjang.getProduct().getNomorMenu() + "\t | "
                                        + produkKeranjang.getProduct().getNamaMenu() + "\t | " + produkKeranjang
                                        .getProduct().getHargaMenu() + "\t | " + produkKeranjang.getQty() + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            return produkKeranjang;
                        })
                        .reduce(0, (result, order) -> (int) (result + (order.getProduct().getHargaMenu() *
                                order.getQty())), Integer::sum);
                bw.newLine();
                bw.write("Totalnya yaitu: " + totalHarga);
                bw.newLine();
                bw.write("Terima kasih sudah datang ke Stars Coffee!");
                bw.flush();
            }
            System.out.println();
            System.out.println("Terima kasih sudah datang ke Stars Coffee!");
        } catch (IOException e) {
            System.out.println("File gagal dibuat");
        }
        return targetFile;
    }
}