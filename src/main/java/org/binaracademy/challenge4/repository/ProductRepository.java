package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, value = "select * from product")
    Page<Product> getAllProduct(Pageable pageable);

    @Query(nativeQuery = true, value = "insert into product(productID, product_code, product_name, product_price, merchant_id) " +
            "values(:productId, :productCode, :productName, :productPrice, :merchantID)")
    void submitNewProduct(@Param("productId") String productID, @Param("productCode") String productCode,
                          @Param("productName") String productName, @Param("productPrice") double productPrice,
                          @Param("merchantID") Merchant merchant);

    @Query(nativeQuery = true, value = "update product set product_name = :newProductName where product_name = :oldProductName")
    void editNameProduct(@Param("oldProductName") String oldProductName, @Param("newProductName") String newProductName);

    @Query(nativeQuery = true, value = "update product set product_price = :newProductPrice where product_code = :productCode")
    void editPriceProduct(@Param("newProductPrice") double newProductPrice, @Param("productCode") String productCode);
}