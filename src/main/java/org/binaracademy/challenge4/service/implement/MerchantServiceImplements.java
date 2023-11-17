package org.binaracademy.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class MerchantServiceImplements implements MerchantService {

    @Autowired
    MerchantRepository merchantRepository;

    @Transactional(readOnly = true)
    @Override
    public MerchantResponse getMerchantDetail(String selectedMerchant) {
        log.info("Getting merchant detail information");
        return Optional.ofNullable(merchantRepository.findByMerchantName(selectedMerchant))
                .map(merchant -> MerchantResponse.builder()
                        .merchantCode(merchant.getMerchantCode())
                        .merchantName(merchant.getMerchantName())
                        .merchantLocation(merchant.getMerchantLocation())
                        .merchantOpen(merchant.getOpen())
                        .build())
                .orElse(null);
    }

    @Async
    @Override
    public void addNewMerchant(Merchant merchant) {
        log.info("Processing add new merchant");
        Optional.ofNullable(merchant)
                .map(newMerchant -> merchantRepository.save(merchant))
                .map(result -> {
                    log.info("Successfully added merchant with name: {}", merchant.getMerchantName());
                    return Boolean.TRUE;
                })
                .orElseGet(() -> {
                    log.info("Failed to add new product");
                    return Boolean.FALSE;
                });
        log.info("Add new merchant successful!");
    }

    @Transactional(readOnly = true)
    @Override
    public List<MerchantResponse> showMerchantOpen() {
        log.info("Success get data merchant where merchant open!");
        return merchantRepository.showMerchantOpen().stream()
                .map(merchant -> MerchantResponse.builder()
                        .merchantCode(merchant.getMerchantCode())
                        .merchantName(merchant.getMerchantName())
                        .merchantLocation(merchant.getMerchantLocation())
                        .merchantOpen(merchant.getOpen())
                        .build())
                .collect(Collectors.toList());
    }

    @Async
    @Override
    public void editStatusMerchant(String codeMerchant, Boolean newStatus){
        try {
            Merchant merchant = merchantRepository.findByMerchantCode(codeMerchant);
            if (!Optional.ofNullable(merchant).isPresent()){
                log.info("Merchant is not available!");
            }
            merchantRepository.editOpenMerchant(codeMerchant, newStatus);
            log.info("Update status merchant with merchant code " + codeMerchant + " success! New status: {}", newStatus);
        } catch (Exception e) {
            log.error("Updating merchant status failed, please try again!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<MerchantResponse> getAllMerchant() {
        log.info("Starting to get All merchant");
        return merchantRepository.findAll().stream()
                .map(merchant -> MerchantResponse.builder()
                        .merchantCode(merchant.getMerchantCode())
                        .merchantName(merchant.getMerchantName())
                        .merchantLocation(merchant.getMerchantLocation())
                        .merchantOpen(merchant.getOpen())
                        .build())
                .collect(Collectors.toList());
    }
}