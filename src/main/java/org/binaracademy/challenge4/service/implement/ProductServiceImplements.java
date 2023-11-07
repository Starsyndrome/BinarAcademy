package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.ProductService;
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
        log.info("Getting product detail information");
        return Optional.ofNullable(productRepository.findByProductName(selectedProduct))
                .map(product -> ProductResponse.builder()
                        .productCode(product.get().getProductCode())
                        .productName(product.get().getProductName())
                        .productPrice(product.get().getPrice())
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
    public Product addNewProduct(Product product) {
        log.info("Processing add new product");
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
        return product;
    }

    @Override
    public Boolean updateProductName(String productCode, String newProductName){
        try {
            productRepository.editNameProduct(productCode, newProductName);
            return true;
        } catch (Exception e) {
            log.error("Error");
            return false;
        }
    }

    @Override
    public Boolean updateProductPrice(Double newProductPrice, String productCode){
        try{
            productRepository.editPriceProduct(newProductPrice, productCode);
            return true;
        } catch (Exception e) {
            log.error("Error");
            return false;
        }
    }

    @Override
    public Boolean updateProductCode(String newProductCode, String productName) {
        try {
            productRepository.editCodeProduct(newProductCode, productName);
            return true;
        } catch (Exception e) {
            log.error("Error");
            return false;
        }
    }

    @Override
    public Boolean deleteProductFromName(String productName){
        try {
            productRepository.deleteProductFromName(productName);
            log.info("Deleted product successfully!");
            return true;
        } catch (Exception e) {
            log.error("Error");
            return false;
        }
    }
}