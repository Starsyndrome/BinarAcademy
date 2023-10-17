package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    @Query(nativeQuery = true, value = "select * from merchant")
    Page<Merchant> getAllMerchant(Pageable pageable);

    @Query(nativeQuery = true, value = "insert into merchant(merchantID, merchant_code, merchant_name, merchant_location, merchant_open) " +
            "values(:merchantID, :merchantCode, :merchantName, :merchantLocation, :merchantOpen)")
    void submitNewMerchant(@Param("merchantID") String merchantID, @Param("merchantCode") String merchantCode,
                              @Param("merchantName") String merchantName, @Param("merchantLocation") String merchantLocation,
                              @Param("merchantOpen") Boolean merchantOpen);

    @Query(nativeQuery = true, value = "update merchant set merchant_open = :newStatus where merchant_open = :oldStatus")
    void editOpenMerchant (@Param("oldStatus") Boolean oldStatus, @Param("newStatus") Boolean newStatus);


    @Query(nativeQuery = true, value = "select * from merchant where merchant_open = :status")
    Boolean showMerchantOpen(@Param("status") Boolean statusMerch);
}