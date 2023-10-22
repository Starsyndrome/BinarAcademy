package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MerchantServiceImplements implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;


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

    @Override
    public void addNewMerchant(Merchant merchant) {
        log.info("Process add new merchant");
        Optional.ofNullable(merchant)
                .map(newMerchant -> merchantRepository.save(merchant))
                .map(result -> {
                    boolean isSuccess = true;
                    log.info("Successfully added merchant with name: {}", merchant.getMerchantName());
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.info("Failed to add new product");
                    return Boolean.FALSE;
                });
        log.info("Successfully add new merchant!");
    }

    @Override
    public Page<Merchant> getAllMerchantPage(int page) {
        log.info("Success get all data in merchant!");
        return merchantRepository.getAllMerchant(PageRequest.of(page, 3));
    }

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

    @Override
    public void editStatusMerchant(String codeMerchant, Boolean newStatus){
        try {
            merchantRepository.editOpenMerchant(codeMerchant, newStatus);
        } catch (Exception e) {
            log.error("Error");
        }
    }
}