package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class ProductServiceImplements implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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

    @Async
    @Override
    public void addNewProduct(Product product) {
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
    }

    @Async
    @Override
    public void updateProductName(String productCode, String newProductName){
        try {
            Product product = productRepository.findByProductCode(productCode);
            if (!Optional.ofNullable(product).isPresent()){
                log.info("Product is not available");
            }
            productRepository.editNameProduct(productCode, newProductName);
            log.info("Updating product name is successful, new product name: " + newProductName);
        } catch (Exception e) {
            log.error("Updating product name failed, please try again!");
        }
    }

    @Async
    @Override
    public void updateProductPrice(Double newProductPrice, String productCode){
        try{
            Product product = productRepository.findByProductCode(productCode);
            if (!Optional.ofNullable(product).isPresent()){
                log.info("Product is not available");
            }
            productRepository.editPriceProduct(newProductPrice, productCode);
            log.info("Updating product price is successful, new product price: " + newProductPrice);
        } catch (Exception e) {
            log.error("Updating product price failed, please try again!");
        }
    }

    @Async
    @Override
    public void updateProductCode(String newProductCode, String productName) {
        try {
            Product product = productRepository.findByProductName(productName).orElse(null);
            if (!Optional.ofNullable(product).isPresent()){
                log.info("Product is not available");
            }
            productRepository.editCodeProduct(newProductCode, productName);
            log.info("Updating product code successful, new product code: " + newProductCode);
        } catch (Exception e) {
            log.error("Updating product code failed, please try again!");
        }
    }

    @Async
    @Override
    public void deleteProductFromName(String productName){
        try {
            Product product = productRepository.findByProductName(productName).orElse(null);
            if (!Optional.ofNullable(product).isPresent()){
                log.info("Product is not available");
            }
            productRepository.deleteProductFromName(productName);
            log.info("Deleted product successfully!");
        } catch (Exception e) {
            log.error("Deleting product failed, please try again!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getProductWithPagination(int page) {
        try {
            log.info("Trying to get products with pagination");
            Page<Product> products = productRepository.getProductWithPagination(PageRequest.of(page, 3));
            log.info("Success get all product with pagination!");
            return products.map(product -> ProductResponse.builder()
                    .productName(product.getProductName())
                    .productCode(product.getProductCode())
                    .productPrice(product.getPrice())
                    .build());
        } catch (Exception e) {
            log.error("Error getting product with pagination");
            throw e;
        }
    }
}