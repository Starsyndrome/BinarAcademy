package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MerchantService {
    Boolean addnewMerchant(Merchant merchant); // Auto generate id
    Page<Merchant> getAllMerchantPaged(int page);
    List<Merchant> showMerchantOpen();
    Boolean submitNewMerchant(Merchant merchant); //id bisa diisi sendiri
    void editStatusMerchant(String codeMerchant, Boolean newStatus, String Id);
}