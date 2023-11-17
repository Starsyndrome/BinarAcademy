package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductName(String productName);

    Product findByProductCode(String productCode);

    @Modifying
    @Query(nativeQuery = true, value = "delete from product where product_name = :productName")
    void deleteProductFromName(@Param("productName") String productName);

    @Modifying
    @Query(nativeQuery = true, value = "update product set product_name = :newProductName where product_code = :productCode")
    void editNameProduct(@Param("productCode") String productCode, @Param("newProductName") String newProductName);

    @Modifying
    @Query(nativeQuery = true, value = "update product set product_price = :newProductPrice where product_code = :productCode")
    void editPriceProduct(@Param("newProductPrice") Double newProductPrice, @Param("productCode") String productCode);

    @Modifying
    @Query(nativeQuery = true, value = "update product set product_code = :newProductCode where product_name = :productName")
    void editCodeProduct(@Param("newProductCode") String newProductCode, @Param("productName") String productName);
}