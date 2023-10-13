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
public class ProductServiceImplements implements ProductService{

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
                .orElseGet(()-> {
                    log.info("Produk gagal disimpan di database");
                    return Boolean.FALSE;
                });
    }

    @Override
    public Page<Product> getAllProductPaged(int page) {
        return productRepository.getAllProduct(PageRequest.of(page, 3));
    }

    @Override
    public Boolean submitNewProduct(Product product) {
        try {
            productRepository.submitNewProduct(product.getProductID(), product.getProductCode(),
                    product.getProductName(), product.getPrice());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateProductFromName(String oldProductName, String newProductName) {
        try{
            productRepository.editNameProduct(oldProductName, newProductName);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateProductFromPrice(double oldProductPrice, double newProductPrice) {
        try{
            productRepository.editPriceProduct(oldProductPrice, newProductPrice);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean deleteProductFromName(String productName) {
        try{
            productRepository.deleteProductFromName(productName);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}