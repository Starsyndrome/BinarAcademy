package org.binaracademy.challenge4.service.spy;

import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.service.implement.MerchantServiceImplements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class MerchantServiceTestSpy {

    @Autowired
    MerchantServiceImplements merchantService;

    @SpyBean
    MerchantRepository merchantRepository;

    @Test
    void getAllMerchant_Test() {
        merchantService.addNewMerchant(Merchant.builder()
                .merchantCode("KDKP")
                .merchantName("Kedai Kopi")
                .merchantLocation("Jakarta Selatan")
                .open(true)
                .build());

        Mockito.when(merchantRepository.findAll()).thenReturn(Arrays.asList(Merchant.builder()
                .merchantCode("KDCF")
                .merchantName("Kedai Coffee")
                .merchantLocation("Setiabudi")
                .open(false)
                .build()));

        List<MerchantResponse> merchants = merchantService.getAllMerchant();
        Mockito.verify(merchantRepository, Mockito.times(1)).findAll();

        Assertions.assertNotNull(merchants);
        Assertions.assertFalse(merchants.isEmpty());
        Assertions.assertEquals(1, merchants.size());
    }

    @Test
    void showMerchantOpen() {
        merchantService.addNewMerchant(Merchant.builder()
                .merchantCode("MTST")
                .merchantName("Merchant Spy Test")
                .merchantLocation("Bogor")
                .open(true)
                .build());

        Mockito.when(merchantRepository.showMerchantOpen()).thenReturn(Arrays.asList(Merchant.builder()
                .merchantName("Merchant Spy")
                .open(true)
                .build()));

        List<MerchantResponse> merchantResponses = merchantService.showMerchantOpen();
        Mockito.verify(merchantRepository, Mockito.times(1)).showMerchantOpen();

        Assertions.assertEquals(1, merchantResponses.size());
        Assertions.assertFalse(merchantResponses.isEmpty());
        Assertions.assertNotNull(merchantResponses);
    }
}