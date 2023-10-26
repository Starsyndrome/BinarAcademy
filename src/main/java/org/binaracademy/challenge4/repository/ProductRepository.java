package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductName(String productName);

    @Query(nativeQuery = true, value = "delete from product where product_name = :productName")
    void deleteProductFromName(@Param("productName") String productName);

    @Query(nativeQuery = true, value = "update product set product_name = :newProductName where product_name = :oldProductName")
    void editNameProduct(@Param("oldProductName") String oldProductName, @Param("newProductName") String newProductName);

    @Query(nativeQuery = true, value = "update product set product_price = :newProductPrice where product_code = :productCode")
    void editPriceProduct(@Param("newProductPrice") Double newProductPrice, @Param("productCode") String productCode);
}