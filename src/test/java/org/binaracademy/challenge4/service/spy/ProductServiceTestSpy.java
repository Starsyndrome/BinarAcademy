package org.binaracademy.challenge4.service.spy;

import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.implement.ProductServiceImplements;
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
public class ProductServiceTestSpy {

    @Autowired
    ProductServiceImplements productService;

    @SpyBean
    ProductRepository productRepository;

    @Test
    void getAllProduct_Test(){
        productService.addNewProduct(Product.builder()
                        .productCode("ESKR")
                        .productName("Es Krim")
                .build());

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(Product.builder()
                        .productCode("ICCR")
                        .productName("Ice Cream")
                        .price(10000.0)
                .build()));

        List<ProductResponse> productResponses = productService.getAllProduct();
        Mockito.verify(productRepository, Mockito.times(1)).findAll();

        Assertions.assertNotNull(productResponses);
        Assertions.assertFalse(productResponses.isEmpty());
        Assertions.assertEquals(1, productResponses.size());
    }
}