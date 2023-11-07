package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.DTO.response.ProductResponse;

import java.util.List;


public interface ProductService {

    ProductResponse getProductDetail(String selectedProduct);

    List<ProductResponse> getAllProduct();

    Product addNewProduct(Product product);

    Boolean updateProductName(String productCode, String newProductName);

    Boolean updateProductPrice(Double newProductPrice, String productCode);

    Boolean updateProductCode(String newProductCode, String productName);

    Boolean deleteProductFromName(String productName);
}