package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.domain.Page;

public interface MerchantService {
    Boolean addnewMerchant(Merchant merchant);
    Page<Merchant> getAllMerchantPaged(int page);
    Boolean showMerchantOpen(Boolean statusMerchant);
//    Boolean submitNewMerchant(Merchant merchant);
    void editStatusMerchant(Boolean oldStatus, Boolean newStatus, String Id);
}