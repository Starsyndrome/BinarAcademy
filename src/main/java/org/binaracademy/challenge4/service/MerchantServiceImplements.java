package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MerchantServiceImplements implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;


    @Override
    public Boolean addnewMerchant(Merchant merchant) {
        return Optional.ofNullable(merchant)
                .map(newMerchant -> merchantRepository.save(merchant))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Merchant berhasil ditambahkan dengan nama merchant: {}", merchant.getMerchantName());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Merchant gagal disimpan di database");
                    return Boolean.FALSE;
                });
    }

    @Override
    public Page<Merchant> getAllMerchantPaged(int page) {
        return merchantRepository.getAllMerchant(PageRequest.of(page, 3));
    }

//    @Override
//    public Page<Merchant> showMerchantOpen(int page) {
//        return merchantRepository.showMerchantOpen(PageRequest.of(page,3));
//    }

    @Override
    public Boolean submitNewMerchant(Merchant merchant) {
        return merchantRepository.submitNewMerchant(merchant.getMerchantID(), merchant.getMerchantCode(),
                merchant.getMerchantName(), merchant.getMerchantLocation(), merchant.getOpen());
    }

    @Override
    public Boolean editStatusMerchant(Boolean oldStatus, Boolean newStatus) {
        return merchantRepository.editOpenMerchant(oldStatus, newStatus);
    }
}