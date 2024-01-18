package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.DTO.response.ErrorResponse;
import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.DTO.response.Response;
import org.binaracademy.challenge4.DTO.responseController.ProductCodeUpdate;
import org.binaracademy.challenge4.DTO.responseController.ProductNameUpdate;
import org.binaracademy.challenge4.DTO.responseController.ProductPriceUpdate;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/{page}")
    public ResponseEntity<Page<ProductResponse>> getProductWithPagination(@PathVariable int page){
        return ResponseEntity.ok()
                .body(productService.getProductWithPagination(page));
    }

    @GetMapping(value = "/getAllProduct", produces = "application/json")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok()
                .body(productService.getAllProduct());
    }

    @PostMapping(value = "/addProduct", consumes = "application/json")
    public ResponseEntity<String> addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return ResponseEntity.ok()
                .body("Add new product with product name: " + product.getProductName() + " successfully!");
    }

    @PutMapping(value = "/update/productName/{productCode}")
    public ResponseEntity<ProductNameUpdate> updateProductName(@RequestParam("newProductName") String newProductName,
                                    @PathVariable("productCode") String productCode,
                                    @RequestBody ProductResponse productResponse){
        productService.updateProductName(productCode,newProductName);
        return ResponseEntity.ok()
                .body(ProductNameUpdate.builder()
                        .productCode(productCode)
                        .newProductName(newProductName)
                        .info("Update product name successfully!")
                        .build());
    }

    @PutMapping(value = "/update/productPrice/{productCode}")
    public ResponseEntity<ProductPriceUpdate> updateProductPrice(@RequestParam("newProductPrice") Double newProductPrice,
                                     @PathVariable("productCode") String productCode,
                                     @RequestBody ProductResponse productResponse){
        productService.updateProductPrice(newProductPrice, productCode);
        return ResponseEntity.ok()
                .body(ProductPriceUpdate.builder()
                        .productCode(productCode)
                        .productPrice(newProductPrice)
                        .info("Update product price successfully!")
                        .build());
    }

    @PutMapping(value = "/update/productCode/{productName}")
    public ResponseEntity<ProductCodeUpdate> updateProductCode(@RequestParam("newProductCode") String newProductCode,
                                            @PathVariable("productName") String productName,
                                            @RequestBody ProductResponse productResponse){
        productService.updateProductCode(newProductCode, productName);
        return ResponseEntity.ok()
                .body(ProductCodeUpdate.builder()
                        .productName(productName)
                        .productCode(newProductCode)
                        .info("Update product code successfully!")
                        .build());
    }

    @DeleteMapping(value = "/delete/{productName}")
    public ResponseEntity<String> deleteProductFromName(@PathVariable("productName") String productName){
        productService.deleteProductFromName(productName);
        return ResponseEntity.ok()
                .body("Product with name: " + productName + " successfully deleted");
    }

    @GetMapping(value = "/getProductDetail")
    @Operation(summary = "Getting detail of one product by product name")
    public ResponseEntity<Response<Object>> getProductDetail(@RequestParam("productName") String productName){
        ProductResponse productResponse = productService.getProductDetail(productName);
        if (Objects.nonNull(productResponse)) {
            return new ResponseEntity<>(Response.builder()
                    .Data(productService.getProductDetail(productName))
                    .isSuccess(Boolean.TRUE)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .Error(ErrorResponse.builder()
                        .errorMessage("Product with name " + productName + " not found")
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build())
                .Data(null)
                .isSuccess(Boolean.FALSE)
                .build(), HttpStatus.NOT_FOUND);
    }
}