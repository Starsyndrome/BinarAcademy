 package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.service.MerchantService;
import org.binaracademy.challenge4.service.OrderService;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
public class StarsCoffeeController {
    /* Maaf ya kak udah ngaco banget, saya ga bisa ngikutin materi sebanyak ini dengan cepat.
    Susah banget rasanya, mau nyerah juga udah nanggung ga bisa, saya pengennya bener-bener
    review materi ini lagi, implementasinya susah betul, kayak otak saya ga sampe gitu kak ya Allah wkwk
     */
    @PostConstruct
    public void init() {
        try {
            this.mainMenu();
        }catch (InputMismatchException ime){
            System.out.println("Input harus berupa angka!");
        }
    }

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    ProductService productService;
    @Autowired
    MerchantService merchantService;

    @Autowired
    OrderService orderService;

    public void mainMenu() throws InputMismatchException{
        log.info("Processing mainMenu()");
        System.out.println("======================================");
        System.out.println("\tSelamat datang di Stars Coffee");
        System.out.println("======================================");
        System.out.println("Silakan pilih menu yang tersedia:");
        System.out.println("1. Lihat produk yang tersedia");
        System.out.println("2. Lihat merchant yang tersedia");
        System.out.println("3. Tambahkan produk");
        System.out.println("4. Tambahkan merchant");
        System.out.println("5. Order");
        System.out.println("0. Keluar");
        System.out.print("Inputkan pilihan => ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        switch (pilihan){
            case 1:
                try {
                    this.productCurrentlyAvailable(null);
                    break;
                }catch (InputMismatchException | IllegalArgumentException ime){
                    this.mainMenu();
                }
                break;
            case 2:
                try {
                    this.merchantCurrentlyAvailable(null);
                    break;
                }catch (InputMismatchException | IllegalArgumentException ime){
                    this.mainMenu();
                }
            case 3:
                this.addNewProduct();
                break;
            case 4:
                this.addNewMerchant();
                break;
            case 5:
                this.newOrder();
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

    // Ini udah ngaco banget
    public void newOrder(){
        System.out.print("Inputkan nama: ");
        String namaPelanggan = scanner.nextLine();
        OrderResponse orderResponse = orderService.newOrder(namaPelanggan);
        System.out.print("Nama: " + orderResponse.getProductName());
        System.out.println("Harga: " + orderResponse.getProductPrice());
        System.out.println("Total: " + orderResponse.getTotalPrice());
    }

    public void merchantCurrentlyAvailable(Page<Merchant> merchants) throws InputMismatchException, IllegalArgumentException{
        System.out.println("--------------------------------------------------------------");
        System.out.println("Nama Merchant" + "\t\t  | \t" + "Lokasi Merchant" + "\t  | \t" + "Open");
        System.out.println("--------------------------------------------------------------");
        merchants  = Optional.ofNullable(merchants)
                .orElseGet(() -> merchantService.getAllMerchantPaged(0));
        merchants.forEach(merchant -> System.out.println(merchant.getMerchantName() + "\t  | \t"
        + merchant.getMerchantLocation() + "\t\t\t  |  \t" + merchant.getOpen()));
        System.out.println("Halaman " + (merchants.getPageable().getPageNumber() + 1) + " dari " + merchants.getTotalPages());
        System.out.println("Jumlah data: " + merchants.getTotalElements());
        System.out.print("Inputkan halaman yang ingin dituju (Pilih 0 jika ingin ke menu utama) => ");
        int pilihan = scanner.nextInt() - 1;
        scanner.nextLine();
        merchants = merchantService.getAllMerchantPaged(pilihan);
        this.merchantCurrentlyAvailable(merchants);

    }

    public void productCurrentlyAvailable(Page<Product> products) throws InputMismatchException, IllegalArgumentException{
        System.out.println("------------------------------------");
        System.out.println("Nama Produk" + "\t  | \t" + "Harga Produk");
        System.out.println("------------------------------------");
        products = Optional.ofNullable(products)
                .orElseGet(() -> productService.getAllProductPaged(0));
        products.forEach(product -> System.out.println(product.getProductName() + "\t  | \t" + product.getPrice()));
        System.out.println("------------------------------------");
        System.out.println("Halaman " + (products.getPageable().getPageNumber() + 1) + " dari " + products.getTotalPages());
        System.out.println("Jumlah data: " + products.getTotalElements());
        System.out.print("Inputkan halaman yang ingin dituju (Pilih 0 jika ingin ke menu utama) => ");
        int pilihan = scanner.nextInt() - 1;
        scanner.nextLine();
        products = productService.getAllProductPaged(pilihan);
        this.productCurrentlyAvailable(products);
    }

    public void addNewProduct(){
        System.out.print("Kode Produk: ");
        String kodeProduk = scanner.nextLine();
        System.out.print("Nama Produk: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Harga Produk: ");
        Integer hargaProduk = scanner.nextInt();
        Product newProduct = Product.builder()
                .productCode(kodeProduk)
                .productName(namaProduk)
                .price(hargaProduk)
                .build();
        productService.addNewProduct(newProduct);
        this.mainMenu();
    }

    public void addNewMerchant(){
        System.out.print("Kode Merchant: ");
        String kodeMerchant = scanner.nextLine();
        System.out.print("Nama Merchant: ");
        String namaMerchant = scanner.nextLine();
        System.out.print("Lokasi Merchant: ");
        String lokasiMerchant = scanner.nextLine();
        System.out.print("Open (true or false): ");
        Boolean openMerchant = scanner.nextBoolean();
        Merchant newMerchant = Merchant.builder()
                .merchantCode(kodeMerchant)
                .merchantName(namaMerchant)
                .merchantLocation(lokasiMerchant)
                .open(openMerchant)
                .build();
        merchantService.addnewMerchant(newMerchant);
        this.mainMenu();
    }
}