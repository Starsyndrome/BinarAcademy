package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    @Query(nativeQuery = true, value = "select * from merchant")
    Page<Merchant> getAllMerchant(Pageable pageable);
}