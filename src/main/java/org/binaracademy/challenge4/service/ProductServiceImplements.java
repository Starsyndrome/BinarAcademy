package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImplements implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse getProductDetail(String selectedProduct) {
        return Optional.ofNullable(productRepository.findByProductName(selectedProduct))
                .map(product -> ProductResponse.builder()
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .productPrice(product.getPrice())
                        .build())
                .orElse(null);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        log.info("Success get all product!");
        return productRepository.findAll().stream()
                .map(products -> ProductResponse.builder()
                        .productCode(products.getProductCode())
                        .productName(products.getProductName())
                        .productPrice(products.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void addNewProduct(Product product) {
        log.info("Process add new product");
        Optional.ofNullable(product)
                .map(newProduct -> productRepository.save(product))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully added product with name: {}", product.getProductName());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Failed to add new product");
                    return Boolean.FALSE;
                });
        log.info("Successfully add new product!");
    }

    @Override
    public void updateProductName(String oldProductName, String newProductName){
        try {
            productRepository.editNameProduct(oldProductName, newProductName);
        } catch (Exception e){
            log.error("Error");
        }
    }

    @Override
    public void updateProductPrice(Double newProductPrice, String productCode){
        try{
            productRepository.editPriceProduct(newProductPrice, productCode);
        } catch (Exception e) {
            log.error("Error");
        }
    }

    @Override
    public void deleteProductFromName(String productName){
        try {
            productRepository.deleteProductFromName(productName);
                log.info("Deleted product successfully!");
        } catch (Exception e) {
            log.error("Error");
        }
    }
}