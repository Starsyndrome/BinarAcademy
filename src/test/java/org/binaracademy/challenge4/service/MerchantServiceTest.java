package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MerchantServiceTest {
    @Autowired
    MerchantService merchantService;

    @Test
    void addNewMerchant_Test(){
        merchantService.addnewMerchant(Merchant.builder()
                        .merchantName("Merchant Test")
                        .merchantCode("KDMT")
                        .merchantLocation("Earth")
                        .open(true)
                .build());
    }

    @Test
        // Bingung ngetestnya :(
    void showMerchantOpen(){
        Assertions.assertFalse(merchantService.showMerchantOpen(true));
    }

    @Test
    void editStatusMerchant_Test(){
        merchantService.editStatusMerchant(false, true,
                "a06d6802-a1dd-4500-b8f6-9ecc4252fb1c");
    }
}
