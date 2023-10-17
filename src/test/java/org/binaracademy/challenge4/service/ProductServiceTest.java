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
                .price(11000)
                .merchant(null)
                .build();
        productRepository.save(product);
        log.info("Add & save product berhasil");
    }

    @Test
    void addNewProduct_Test(){
        productService.addNewProduct(Product.builder()
                        .productCode("TestCode")
                        .productName("Testingg")
                        .price(0)
                        .merchant(null)
                .build());
    }

    @Test
    void submitNewProduct_Test(){
        productService.submitNewProduct(Product.builder()
                        .productID("TestId")
                        .productCode("Code")
                        .productName("Testing")
                        .price(0)
                .build());
    }

    @Test
    void totalElement(){
        Page<Product> productPage = productService.getAllProductPaged(0);
        Assertions.assertEquals(8, productPage.getTotalElements());
    }

    @Test
    void updateProductName_Test(){
        productService.updateProductName("Product Testing 1", "Product Testing",
                "b8aeb5e5-40d1-4e0f-8895-4e2a24e0a3c4");
    }

    @Test
    void updateProductPrice_Test(){
        productService.updateProductPrice("PC1", 10000,
                "b8aeb5e5-40d1-4e0f-8895-4e2a24e0a3c4");
    }

    @Test
    void deleteProductFromId(){
        productService.deleteProductFromId(Product.builder()
                        .productID("3f19893e-c81d-4d2f-8e50-ab30ef82247c")
                .build());
    }
}