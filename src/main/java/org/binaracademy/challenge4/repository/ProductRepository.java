package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, value = "select * from product")
    Page<Product> getAllProduct(Pageable pageable);

    // Ya Allah bingungnya pas diimplemntasiin

//    @Query(nativeQuery = true, value = "select * from product " +
//            "join merchant on merchantid = product.merchantid " +
//            "where product_price <= 15000")
//    Page<Product> underLimaBelas(Pageable pageable);
}
