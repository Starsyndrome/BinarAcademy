package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MerchantServiceTest {
    @Autowired
    MerchantService merchantService;

    @Test
    void addNewMerchant_Test(){ // auto generate id
        merchantService.addnewMerchant(Merchant.builder()
                        .merchantName("Merchant Test")
                        .merchantCode("KDMT")
                        .merchantLocation("Earth")
                        .open(Boolean.FALSE)
                .build());
    }

    @Test
    void submitNewMerchant_Test(){ // id bisa diisi sendiri
        merchantService.submitNewMerchant(Merchant.builder()
                        .merchantID("123TestMerchantId")
                        .merchantCode("CodeMerchant")
                        .merchantName("Test Merchant")
                        .merchantLocation("Jakarta")
                        .open(false)
                .build());
    }

    @Test
    void showMerchantOpen_Test(){
        List<Merchant> merchants = merchantService.showMerchantOpen();
        Assertions.assertEquals(5, merchants.size());
    }

    @Test
    void editStatusMerchant_Test(){
        merchantService.editStatusMerchant("SEFD", Boolean.TRUE,
                "a06d6802-a1dd-4500-b8f6-9ecc4252fb1c");
    }
}
