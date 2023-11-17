package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.DTO.response.ErrorResponse;
import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.DTO.response.Response;
import org.binaracademy.challenge4.DTO.responseController.ProductCodeUpdate;
import org.binaracademy.challenge4.DTO.responseController.ProductNameUpdate;
import org.binaracademy.challenge4.DTO.responseController.ProductPriceUpdate;
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
public class ProductControllerTest {

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
//        Mockito.when(productService.addNewProduct(product)).thenReturn(product);
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

    @Test
    void getProductDetail_Test(){
        Mockito.when(productService.getProductDetail(Mockito.anyString())).thenReturn(ProductResponse.builder()
                        .productCode("PRODUCT")
                        .productName("Product Name")
                        .productPrice(99999.9)
                .build());

        ResponseEntity<Response<Object>> getProductDetail = productController
                .getProductDetail("Product Name");

        ResponseEntity<Response<Object>> expected = new ResponseEntity<>(Response.builder()
                .Data(ProductResponse.builder()
                        .productCode("PRODUCT")
                        .productName("Product Name")
                        .productPrice(99999.9)
                        .build())
                .isSuccess(Boolean.TRUE)
                .build(), HttpStatus.OK);

        Assertions.assertEquals(expected, getProductDetail);
    }

    @Test
    void getProductDetail_Test_Failed(){
        Mockito.when(productService.getProductDetail(Mockito.anyString())).thenReturn(ProductResponse.builder()
                .productCode("PRODUCT")
                .productName("Product Name")
                .productPrice(99999.9)
                .build());

        ResponseEntity<Response<Object>> getProductDetail = productController
                .getProductDetail(null);

        ResponseEntity<Response<Object>> expected = new ResponseEntity<>(Response.builder()
                .Data(null)
                .Error(ErrorResponse.builder()
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .errorMessage("Product with name " + null + " not found")
                        .build())
                .isSuccess(Boolean.FALSE)
                .build(), HttpStatus.NOT_FOUND);

        Assertions.assertEquals(expected, getProductDetail);
    }

    @Test
    void updateProductName_Test(){
        ProductResponse product = ProductResponse.builder()
                .productCode("CODE")
                .productName("Product Test")
                .productPrice(0.0)
                .build();

//        Mockito.when(productService.updateProductName(Mockito.anyString(), Mockito.anyString()))
//                .thenReturn(true);

        ResponseEntity<ProductNameUpdate> updateProductName = productController
                .updateProductName("Product Controller Test", "CODE", product);
        Mockito.verify(productService, Mockito.times(1))
                        .updateProductName(Mockito.anyString(), Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, updateProductName.getStatusCode());
        Assertions.assertEquals("Product Controller Test",
                Objects.requireNonNull(updateProductName.getBody()).getNewProductName());
    }

    @Test
    void updateProductPrice_Test(){
        ProductResponse product = ProductResponse.builder()
                .productCode("CODE")
                .productName("Product Test")
                .productPrice(0.0)
                .build();

//        Mockito.when(productService.updateProductPrice(Mockito.anyDouble(), Mockito.anyString()))
//                .thenReturn(true);

        ResponseEntity<ProductPriceUpdate> updateProductPrice = productController
                .updateProductPrice(99999.9, "CODE", product);
        Mockito.verify(productService, Mockito.times(1))
                        .updateProductPrice(Mockito.anyDouble(), Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, updateProductPrice.getStatusCode());
        Assertions.assertEquals(99999.9, Objects.requireNonNull(updateProductPrice.getBody()).getProductPrice());
    }

    @Test
    void updateProductCode_Test(){
        ProductResponse product = ProductResponse.builder()
                .productCode("CODE")
                .productName("Product Test")
                .productPrice(0.0)
                .build();

//        Mockito.when(productService.updateProductCode(Mockito.anyString(), Mockito.anyString()))
//                .thenReturn(true);

        ResponseEntity<ProductCodeUpdate> updateProductCode = productController
                .updateProductCode("PRCD", "Product Test", product);
        Mockito.verify(productService, Mockito.times(1))
                .updateProductCode(Mockito.anyString(), Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, updateProductCode.getStatusCode());
        Assertions.assertEquals("PRCD", Objects.requireNonNull(updateProductCode.getBody()).getProductCode());
    }

    @Test
    void deleteProductByName(){
        Mockito.when(productService.getAllProduct()).thenReturn(Arrays.asList(ProductResponse.builder()
                        .productCode("CODE")
                        .productName("Product Name")
                        .productPrice(0.0)
                .build()));

        ResponseEntity<String> deleteProductFromName = productController.deleteProductFromName("Product Name");
        Mockito.verify(productService, Mockito.times(1)).deleteProductFromName(Mockito.anyString());

        Assertions.assertEquals(HttpStatus.OK, deleteProductFromName.getStatusCode());
    }
}