package org.binaracademy.challenge4.service.mock;

import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.implement.ProductServiceImplements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTestMock {

    @InjectMocks
    ProductServiceImplements productService;

    @Mock
    ProductRepository productRepository;

    @Test
    void getProductDetail_Test(){
        Mockito.when(productRepository.findByProductName(Mockito.anyString())).thenReturn(Optional.ofNullable(Product.builder()
                .productCode("MRTB")
                .productName("Martabak")
                .price(20000.0)
                .merchant(Merchant.builder()
                        .merchantName("Kedai Kue")
                        .build())
                .build()));

        ProductResponse response = productService.getProductDetail("Martabak");
        Mockito.verify(productRepository, Mockito.times(1)).findByProductName(Mockito.anyString());

        Assertions.assertEquals("MRTB", response.getProductCode());
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getProductName().isEmpty());
    }

    @Test
    void deleteProduct_Test(){
        Product product = Product.builder()
                .productCode("MRTB")
                .price(20000.0)
                .build();

        Mockito.when(productRepository.findByProductName("Martabak")).thenReturn(Optional.ofNullable(product));
        Assertions.assertAll(() -> productService.deleteProductFromName("Martabak"));

    }

//    @Test
//    void updateProductName_Test(){
//        Mockito.when(productRepository.findByProductCode("TEST")).thenReturn(Product.builder()
//                        .productCode("TEST")
//                        .productName("Product Test")
//                .build());
//
//       Boolean updateProductName = productService.updateProductName("TEST", "Product Test Mock");
//       Mockito.verify(productRepository, Mockito.times(1)).editNameProduct(Mockito.anyString(), Mockito.anyString());
//
//       Assertions.assertTrue(updateProductName);
//    }

//    @Test
//    void updateProductPrice_Test(){
//        Mockito.when(productRepository.findByProductCode("TEST")).thenReturn(Product.builder()
//                        .productCode("TEST")
//                        .price(10000.0)
//                .build());
//
//        Boolean updateProductPrice = productService.updateProductPrice(20000.0, "TEST");
//        Mockito.verify(productRepository, Mockito.times(1)).editPriceProduct(Mockito.anyDouble(), Mockito.anyString());
//
//        Assertions.assertTrue(updateProductPrice);
//    }

//    @Test
//    void updateProductCode_Test() {
//        Mockito.when(productRepository.findByProductName("Product Test")).thenReturn(Optional.ofNullable(Product.builder()
//                .productCode("TEST")
//                .productName("Product Test")
//                .build()));
//
//        Boolean updateProductCode = productService.updateProductCode("TESTING", "Product Test");
//        Mockito.verify(productRepository, Mockito.times(1)).editCodeProduct(Mockito.anyString(), Mockito.anyString());
//
//        Assertions.assertTrue(updateProductCode);
//    }
}