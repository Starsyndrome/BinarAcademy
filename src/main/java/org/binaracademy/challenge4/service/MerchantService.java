package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;

import java.util.List;

public interface MerchantService {

    MerchantResponse getMerchantDetail(String selectedMerchant);

    void addNewMerchant(Merchant merchant);

    List<MerchantResponse> showMerchantOpen();

    void editStatusMerchant(String codeMerchant, Boolean newStatus);

    List<MerchantResponse> getAllMerchant();
}