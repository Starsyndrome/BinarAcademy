package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


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
                        .price(99999.0)
                        .merchant(Merchant.builder()
                                .merchantID("25cbe13c-4671-453d-a696-3750dce29ec9")
                                .build())
                .build());
    }

//    @Test
//    void updateProductName_Test(){
//        productService.updateProductName("Product Test", "Product Testing",
//                "5e351186-8c30-4a40-9f50-9dce62693a44");
//    }

//    @Test
//    void updateProductPrice_Test(){
//        productService.updateProductPrice("CodeProduct", 12345,
//                "5e351186-8c30-4a40-9f50-9dce62693a44");
//    }

    @Test
    void deleteProductFromName(){
        productService.deleteProductFromName("Testing RESTful API");
    }

    @Test
    void getAllProduct_Test(){
        List<ProductResponse> productResponses = productService.getAllProduct();
        Assertions.assertEquals(9, productResponses.size());
    }
}