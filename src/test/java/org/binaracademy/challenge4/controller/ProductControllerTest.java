package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.service.ProductService;
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
public class ProductControllerTest { // Sisanya menyusul kak :(

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Test
    void addNewProduct_Test(){
        Product product = Product.builder()
                .productCode("TEST")
                .productName("Product Test")
                .price(99999.9)
                .merchant(Merchant.builder()
                        .merchantID("merchantidtest")
                        .build())
                .build();

        ResponseEntity<String> addNewProduct = productController.addNewProduct(product);
        Mockito.when(productService.addNewProduct(product)).thenReturn(product);
        Mockito.verify(productService, Mockito.times(1)).addNewProduct(product);

        Assertions.assertEquals(HttpStatus.OK, addNewProduct.getStatusCode());
        Assertions.assertNotNull(addNewProduct);
    }

    @Test
    void getAllProduct_Test(){
        Mockito.when(productService.getAllProduct()).thenReturn(Arrays.asList(ProductResponse.builder()
                        .productCode("TEST")
                        .productName("Product Name")
                        .productPrice(99999.9)
                .build()));

        ResponseEntity<List<ProductResponse>> getAllProduct = productController.getAllProduct();
        Mockito.verify(productService, Mockito.times(1)).getAllProduct();

        Assertions.assertEquals(HttpStatus.OK, getAllProduct.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(getAllProduct.getBody()).size());
        Assertions.assertNotNull(getAllProduct);
    }
}