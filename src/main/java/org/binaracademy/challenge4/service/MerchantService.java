package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;

import java.util.List;

public interface MerchantService {

    MerchantResponse getMerchantDetail(String selectedMerchant);

    Merchant addNewMerchant(Merchant merchant);

    List<MerchantResponse> showMerchantOpen();

    Boolean editStatusMerchant(String codeMerchant, Boolean newStatus);

    List<MerchantResponse> getAllMerchant();
}