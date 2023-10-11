package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImplements implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Boolean addNewProduct(Product product) {
        log.info("Proses menambahkan produk baru");
        return Optional.ofNullable(product)
                .map(newProduct -> productRepository.save(product))
                .map(result -> {
                    boolean isSuccess = Objects.nonNull(result);
                    if (isSuccess) {
                        log.info("Produk berhasil ditambahkan dengan nama produk: {}", product.getProductName());
                    }
                    return isSuccess;
                })
                .orElseGet(()-> {
                    log.info("Produk gagal disimpan dengan nama: {}", product.getProductName());
                    return Boolean.FALSE;
                });
    }

    @Override
    public Page<Product> getAllProductPaged(int page) {
        return productRepository.getAllProduct(PageRequest.of(page, 3));
    }

    @Override
    public Boolean submitNewProduct(Product product) {
        return productRepository.submitNewProduct(product.getProductID(), product.getProductCode(),
                product.getProductName(), product.getPrice());
    }

    @Override
    public Boolean updateProductFromName(String oldProductName, String newProductName) {
        return productRepository.editNameProduct(oldProductName, newProductName);
    }

    @Override
    public Boolean updateProductFromPrice(double oldProductPrice, double newProductPrice) {
        return productRepository.editPriceProduct(oldProductPrice, newProductPrice);
    }

    @Override
    public Boolean deleteProductFromName(String productName) {
        return productRepository.deleteProductFromName(productName);
    }
}