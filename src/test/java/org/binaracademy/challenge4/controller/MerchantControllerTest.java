package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.service.MerchantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest { // Sisanya menyusul kak :(

    @InjectMocks
    MerchantController merchantController;

    @Mock
    MerchantService merchantService;

    @Test
    void addNewMerchant_Test(){
        Merchant merchant = Merchant.builder()
                .merchantCode("TEST")
                .merchantName("Product Test")
                .merchantLocation("Jakarta")
                .open(true)
                .build();

        ResponseEntity<String> addNewMerchant = merchantController.addNewMerchant(merchant);
        Mockito.when(merchantService.addNewMerchant(merchant)).thenReturn(merchant);
        Mockito.verify(merchantService, Mockito.times(1)).addNewMerchant(merchant);

        Assertions.assertEquals(HttpStatus.OK, addNewMerchant.getStatusCode());
        Assertions.assertNotNull(addNewMerchant);
    }

    @Test
    void showMerchantOpen_Test(){
        Mockito.when(merchantService.showMerchantOpen()).thenReturn(Arrays.asList(MerchantResponse.builder()
                .merchantCode("TEST")
                .merchantName("Product Name")
                .merchantLocation("Jakarta")
                .merchantOpen(true)
                .build()));

        ResponseEntity<List<MerchantResponse>> merchantOpen = merchantController.merchantOpen();
        Mockito.verify(merchantService, Mockito.times(1)).showMerchantOpen();

        Assertions.assertEquals(HttpStatus.OK, merchantOpen.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(merchantOpen.getBody()).size());
        Assertions.assertNotNull(merchantOpen);
    }
}