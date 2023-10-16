package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.domain.Page;


public interface ProductService {

    Boolean addNewProduct(Product product); // Auto generate id
    Page<Product> getAllProductPaged(int page);
    Boolean submitNewProduct(Product product); // id bisa diisi sendiri
    void updateProductName(String oldProductName, String newProductName, String Id);
    void updateProductPrice(String productCode, double newProductPrice, String Id);
    void deleteProductFromId(Product product);
}