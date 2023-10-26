package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;

import java.util.List;


public interface ProductService {
    ProductResponse getProductDetail(String selectedProduct);

    List<ProductResponse> getAllProduct();

    void addNewProduct(Product product); // Auto generate id
//    void updateProductName(String oldProductName, String newProductName, String Id);

    void updateProductName(String oldProductName, String newProductName);
//    void updateProductPrice(String productCode, double newProductPrice, String Id);

    void updateProductPrice(Double newProductPrice, String productCode);
//    void deleteProductFromName(Product product);

    void deleteProductFromName(String productName);
}