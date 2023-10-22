package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.model.response.Response;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/getAllProduct", produces = "application/json")
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping(value = "/addProduct", consumes = "application/json")
    public String addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return "Add new product with product name: " + product.getProductName() + " successfully!";
    }

    @PutMapping(value = "/updateProductName/{productName}")
    @Operation(summary = "Update Product Name with Product Name")
    public String updateProductName(@RequestParam("newProductName") String newProductName,
                                    @PathVariable("productName") String oldProductName,
                                    @RequestBody ProductResponse productResponse){
        productService.updateProductName(oldProductName,newProductName);
        return "Update product name successfully, new product name: " + newProductName;
    }

    @PutMapping(value = "/updateProductPrice/{productCode}")
    @Operation(summary = "Update Product Price with Product Code")
    public String updateProductPrice(@RequestParam("newProductPrice") Double newProductPrice,
                                     @PathVariable("productCode") String productCode,
                                     @RequestBody ProductResponse productResponse){
        productService.updateProductPrice(newProductPrice, productCode);
        return "Update product price successfully, new product price: " + newProductPrice;
    }

    @DeleteMapping(value = "/deleteProduct/{productName}")
    public String deleteProductFromName(@PathVariable("productName") String productName){
        productService.deleteProductFromName(productName);
        return "Product with name: " + productName + " successfully deleted";
    }

    @GetMapping(value = "/getProductDetail")
    @Operation(summary = "Getting detail of one product by product name")
    public ResponseEntity getProductDetail(@RequestParam("productName") String productName){
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