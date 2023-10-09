package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MerchantController {

    @Autowired
    MerchantService merchantService;
    private Scanner scanner = new Scanner(System.in);

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
    }

    public void merchantCurrentlyAvailable(Page<Merchant> merchants) throws InputMismatchException, IllegalArgumentException{
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Nama Merchant" + "\t\t  | \t" + "Kode Merchant" + "\t |\t" +"Lokasi Merchant" + "\t  | \t" + "Open");
        System.out.println("-------------------------------------------------------------------------------------");
        merchants  = Optional.ofNullable(merchants)
                .orElseGet(() -> merchantService.getAllMerchantPaged(0));
        merchants.forEach(merchant -> System.out.println(merchant.getMerchantName() + "\t  | \t\t" + merchant.getMerchantCode() +
                "\t\t  |  \t\t" + merchant.getMerchantLocation() + "\t\t  |  \t" + merchant.getOpen()));
        System.out.println("Halaman " + (merchants.getPageable().getPageNumber() + 1) + " dari " + merchants.getTotalPages());
        System.out.println("Jumlah data: " + merchants.getTotalElements());
        System.out.print("Inputkan halaman yang ingin dituju (Pilih 0 jika ingin ke menu utama) => ");
        int pilihan = scanner.nextInt() - 1;
        scanner.nextLine();
        merchants = merchantService.getAllMerchantPaged(pilihan);
        this.merchantCurrentlyAvailable(merchants);
    }
}
