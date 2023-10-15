package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
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
    @Autowired
    ProductRepository productRepository;

    @Test
    void submitNewProductTest(){
        Product product = Product.builder()
                .productCode("PC2")
                .productName("Product Testing 2")
                .price(0)
                .merchant(null)
                .build();
        productRepository.save(product);
        log.info("Add & save product berhasil");
    }

    @Test
    void totalElement(){
        Page<Product> productPage = productService.getAllProductPaged(0);
        Assertions.assertEquals(8, productPage.getTotalElements());
    }
}