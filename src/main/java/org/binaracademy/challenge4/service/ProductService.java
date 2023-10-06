package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.domain.Page;


public interface ProductService {

    Boolean addNewProduct(Product product);
    Page<Product> getAllProductPaged(int page);
}