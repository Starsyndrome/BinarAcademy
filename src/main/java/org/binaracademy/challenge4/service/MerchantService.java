package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MerchantService {
    MerchantResponse getMerchantDetail(String selectedMerchant);
    void addNewMerchant(Merchant merchant); // Auto generate id
    Page<Merchant> getAllMerchantPage(int page);
    List<MerchantResponse> showMerchantOpen();
//    void editStatusMerchant(String codeMerchant, Boolean newStatus, String Id);
    void editStatusMerchant(String codeMerchant, Boolean newStatus);
}