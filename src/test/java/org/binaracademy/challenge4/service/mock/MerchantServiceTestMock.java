package org.binaracademy.challenge4.service.mock;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.service.implement.MerchantServiceImplements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class MerchantServiceTestMock {

    @InjectMocks
    MerchantServiceImplements merchantService;

    @Mock
    MerchantRepository merchantRepository;

    @Test
    void getMerchantDetail_Test(){
        Mockito.when(merchantRepository.findByMerchantName(Mockito.anyString())).thenReturn(Merchant.builder()
                        .merchantCode("KDKP")
                        .merchantName("Kedai Kopi")
                        .merchantLocation("Jakarta Selatan")
                        .open(true)
                .build())
                .thenReturn(Merchant.builder()
                        .merchantCode("KDCK")
                        .merchantName("Kedai Cake")
                        .merchantLocation("Jakarta Pusat")
                        .open(false)
                        .build());
        MerchantResponse merchantResponse1 = merchantService.getMerchantDetail("Kedai Kopi");
        MerchantResponse merchantResponse2 = merchantService.getMerchantDetail("Kedai Cake");

        Mockito.verify(merchantRepository, Mockito.times(2)).findByMerchantName(Mockito.anyString());

        Assertions.assertNotNull(merchantResponse1);
        Assertions.assertEquals("Jakarta Selatan", merchantResponse1.getMerchantLocation());
        Assertions.assertEquals(false, merchantResponse2.getMerchantOpen());
        Assertions.assertFalse(merchantResponse2.getMerchantCode().isEmpty());
    }

    @Test
    void updateMerchantStatus_Test(){
        Mockito.when(merchantRepository.findByMerchantCode("TEST")).thenReturn(Merchant.builder()
                        .merchantCode("TEST")
                        .open(true)
                .build());

        Boolean editStatusMerchant = merchantService.editStatusMerchant("TEST", false);
        Mockito.verify(merchantRepository, Mockito.times(1)).editOpenMerchant(Mockito.anyString(), Mockito.anyBoolean());

        Assertions.assertTrue(editStatusMerchant);
    }
}