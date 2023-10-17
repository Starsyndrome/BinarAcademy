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
                .productCode("PC1")
                .productName("Product Testing")
                .price(10000)
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
        productService.updateProductName("Product Testing", "Product Testing Success",
                "5226191c-1d11-41f0-9bc0-e01e24fce780");
    }

    @Test
    void updateProductPrice_Test(){
        productService.updateProductPrice("PC1", 99999,
                "5226191c-1d11-41f0-9bc0-e01e24fce780");
    }

    @Test
    void deleteProductFromId(){
        productService.deleteProductFromId(Product.builder()
                        .productID("5226191c-1d11-41f0-9bc0-e01e24fce780")
                .build());
    }
}