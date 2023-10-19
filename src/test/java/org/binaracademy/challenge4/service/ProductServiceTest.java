package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;


@SpringBootTest
@Slf4j
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    void addNewProduct_Test(){ // id auto generate for product id
        productService.addNewProduct(Product.builder()
                        .productCode("CodeProduct")
                        .productName("Product Test")
                        .price(99999)
                        .merchant(Merchant.builder()
                                .merchantID("25cbe13c-4671-453d-a696-3750dce29ec9")
                                .build())
                .build());
    }

    @Test
    void submitNewProduct_Test(){ // id bisa diisi sendiri
        productService.submitNewProduct(Product.builder()
                        .productID("TestID")
                        .productCode("ProductCode")
                        .productName("Testing")
                        .price(0)
                        .merchant(Merchant.builder()
                                .merchantID("TestMerchantID")
                                .build())
                .build());
    }

    @Test
    void totalElementProduct_Test(){
        Page<Product> productPage = productService.getAllProductPaged(0);
        Assertions.assertEquals(9, productPage.getTotalElements());
    }

    @Test
    void updateProductName_Test(){
        productService.updateProductName("Product Test", "Product Testing",
                "5e351186-8c30-4a40-9f50-9dce62693a44");
    }

    @Test
    void updateProductPrice_Test(){
        productService.updateProductPrice("CodeProduct", 12345,
                "5e351186-8c30-4a40-9f50-9dce62693a44");
    }

    @Test
    void deleteProductFromId(){
        productService.deleteProductFromId(Product.builder()
                        .productID("5e351186-8c30-4a40-9f50-9dce62693a44")
                .build());
    }
}