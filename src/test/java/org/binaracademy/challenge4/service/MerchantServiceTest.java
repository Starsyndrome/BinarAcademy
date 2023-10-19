package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class MerchantServiceTest {
    @Autowired
    MerchantService merchantService;

    @Test
    void addNewMerchant_Test(){ // auto generate id
        merchantService.addnewMerchant(Merchant.builder()
                        .merchantName("Test Merchant")
                        .merchantCode("MerchantTest")
                        .merchantLocation("Jakarta")
                        .open(Boolean.FALSE)
                .build());
    }

    @Test
    void submitNewMerchant_Test(){ // id bisa diisi sendiri
        merchantService.submitNewMerchant(Merchant.builder()
                        .merchantID("MerchantID")
                        .merchantCode("CodeMerchant")
                        .merchantName("Testing Merchant")
                        .merchantLocation("Jakarta")
                        .open(false)
                .build());
    }

    @Test
    void showMerchantOpen_Test(){
        List<Merchant> merchants = merchantService.showMerchantOpen();
        Assertions.assertEquals(2, merchants.size());
    }

    @Test
    void editStatusMerchant_Test(){
        merchantService.editStatusMerchant("MCTS", false,
                "25cbe13c-4671-453d-a696-3750dce29ec9");
    }

    @Test
    void totalElementMerchant_Test(){
        Page<Merchant> merchantPage = merchantService.getAllMerchantPaged(0);
        Assertions.assertEquals(3, merchantPage.getTotalElements());
    }
}
