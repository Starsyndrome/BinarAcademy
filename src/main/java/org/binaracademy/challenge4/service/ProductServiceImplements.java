package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImplements implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Boolean addNewProduct(Product product) {
        log.info("Proses menambahkan produk baru");
        return Optional.ofNullable(product)
                .map(newProduct -> productRepository.save(product))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Produk berhasil ditambahkan dengan nama produk: {}", product.getProductName());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Produk gagal disimpan di database");
                    return Boolean.FALSE;
                });
    }

    @Override
    public Page<Product> getAllProductPaged(int page) {
        log.info("Success get all product!");
        return productRepository.getAllProduct(PageRequest.of(page, 3));
    }

    @Override
    public Boolean submitNewProduct(Product product) {
        try {
            log.info("Submit new product success with name: {}", product.getProductName());
            productRepository.submitNewProduct(product.getProductID(), product.getProductCode(),
                    product.getProductName(), product.getPrice(), product.getMerchant());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void updateProductName(String oldProductName, String newProductName, String Id) {
        try {
            if (productRepository.existsById(Id)) {
                productRepository.editNameProduct(oldProductName, newProductName);
            }
        } catch (Exception e){
                log.error("Error");
        }
    }

    @Override
    public void updateProductPrice(String productCode, double newProductPrice, String Id) {
        try{
            if (productRepository.existsById(Id)) {
            productRepository.editPriceProduct(newProductPrice, productCode);
            }
        } catch (Exception e) {
            log.error("Error");
        }
    }

    @Override
    public void deleteProductFromId(Product product) {
        try {
            if (productRepository.existsById(product.getProductID())) {
                productRepository.deleteById(product.getProductID());
                log.info("Successfully deleted product with ID: {}", product.getProductID());
            }
        } catch (Exception e) {
            log.error("Error");
        }
    }
}