package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findByMerchantCode(String merchantCode);

    Merchant findByMerchantName(String merchantName);

    @Query(nativeQuery = true, value = "update merchant set merchant_open = :newStatus where merchant_code = :codeMerchant")
    void editOpenMerchant(@Param("codeMerchant") String codeMerchant, @Param("newStatus") Boolean newStatus);

    @Query(nativeQuery = true, value = "select * from merchant where merchant_open = true")
    List<Merchant> showMerchantOpen();
}