package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.ErrorResponse;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.DTO.response.Response;
import org.binaracademy.challenge4.DTO.responseController.MerchantOpenUpdate;
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
public class MerchantControllerTest {

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

        ResponseEntity<String> addNewMerchant = merchantController.addNewMercant(merchant);
//        Mockito.when(merchantService.addNewMerchant(merchant)).thenReturn(merchant);
        Mockito.verify(merchantService, Mockito.times(1)).addNewMerchant(merchant);

        Assertions.assertEquals(HttpStatus.OK, addNewMerchant.getStatusCode());
        Assertions.assertNotNull(addNewMerchant);
        Assertions.assertEquals("TEST", merchant.getMerchantCode());
    }

    @Test
    void showMerchantOpen_Test(){
        Mockito.when(merchantService.showMerchantOpen()).thenReturn(Arrays.asList(MerchantResponse.builder()
                .merchantCode("TEST")
                .merchantName("Merchant Name")
                .merchantLocation("Jakarta")
                .merchantOpen(true)
                .build()));

        ResponseEntity<List<MerchantResponse>> merchantOpen = merchantController.merchantOpen();
        Mockito.verify(merchantService, Mockito.times(1)).showMerchantOpen();

        Assertions.assertEquals(HttpStatus.OK, merchantOpen.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(merchantOpen.getBody()).size());
        Assertions.assertNotNull(merchantOpen);
    }

    @Test
    void getMerchantDetail_Test(){
        Mockito.when(merchantService.getMerchantDetail(Mockito.anyString())).thenReturn(MerchantResponse.builder()
                .merchantCode("TEST")
                .merchantName("Merchant Name")
                .merchantLocation("Jakarta")
                .merchantOpen(false)
                .build());

        ResponseEntity<Response<Object>> getMerchantDetail = merchantController
                .getMerchantDetail("Merchant Name");

        ResponseEntity<Response<Object>> expected = new ResponseEntity<>((Response.builder()
                .Data(MerchantResponse.builder()
                        .merchantCode("TEST")
                        .merchantName("Merchant Name")
                        .merchantLocation("Jakarta")
                        .merchantOpen(false)
                        .build())
                .isSuccess(Boolean.TRUE)
                .build()), HttpStatus.OK);

        Assertions.assertEquals(expected, getMerchantDetail);
    }

    @Test
    void getMerchantDetail_Test_Failed(){
        Mockito.when(merchantService.getMerchantDetail(Mockito.anyString())).thenReturn(MerchantResponse.builder()
                .merchantCode("TEST")
                .merchantName("Merchant Name")
                .merchantLocation("Jakarta")
                .merchantOpen(false)
                .build());

        ResponseEntity<Response<Object>> getMerchantDetail = merchantController
                .getMerchantDetail(null);

        ResponseEntity<Response<Object>> expected = new ResponseEntity<>((Response.builder()
                .Data(null)
                .isSuccess(Boolean.FALSE)
                .Error(ErrorResponse.builder()
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .errorMessage("Merchant with name " + null + " not found")
                        .build())
                .build()), HttpStatus.NOT_FOUND);

        Assertions.assertEquals(expected, getMerchantDetail);
    }

    @Test
    void updateMerchantStatus_Test(){
        Merchant merchant = Merchant.builder()
                .merchantCode("TEST")
                .merchantName("Merchant Name")
                .merchantLocation("Jakarta")
                .open(true)
                .build();

//        Mockito.when(merchantService.editStatusMerchant(Mockito.anyString(), Mockito.anyBoolean()))
//                .thenReturn(true);

        ResponseEntity<MerchantOpenUpdate> updateStatusMerchant = merchantController
                .updateStatusMerchant(false, "TEST", merchant);
        Mockito.verify(merchantService, Mockito.times(1))
                .editStatusMerchant(Mockito.anyString(), Mockito.anyBoolean());

        Assertions.assertEquals(HttpStatus.OK, updateStatusMerchant.getStatusCode());
        Assertions.assertEquals(false, Objects.requireNonNull(updateStatusMerchant.getBody()).getStatus());
    }
}