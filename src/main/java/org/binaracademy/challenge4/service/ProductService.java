package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.DTO.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {

    ProductResponse getProductDetail(String selectedProduct);

    List<ProductResponse> getAllProduct();

    void addNewProduct(Product product);

    void updateProductName(String productCode, String newProductName);

    void updateProductPrice(Double newProductPrice, String productCode);

    void updateProductCode(String newProductCode, String productName);

    void deleteProductFromName(String productName);

    Page<ProductResponse> getProductWithPagination(int page);
}