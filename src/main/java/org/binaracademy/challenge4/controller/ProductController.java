package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ProductController {

    @Autowired
    ProductService productService;
    private Scanner scanner = new Scanner(System.in);

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
}