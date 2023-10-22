package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.model.response.Response;
import org.binaracademy.challenge4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping(value = "api/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping(value = "/addMerchant", consumes = "application/json")
    public String addNewMerchant(@RequestBody Merchant merchant){
        merchantService.addNewMerchant(merchant);
        return "Add new merchant with merchant name: " + merchant.getMerchantName() + " successfully!";
    }

    @PutMapping(value = "/updateStatusMerchant/{codeMerchant}")
    public String updateStatusMerchant(@RequestParam("newStatusMerchant") Boolean statusMerchant,
                                       @PathVariable("codeMerchant") String codeMerchant,
                                       @RequestBody Merchant merchant){
        merchantService.editStatusMerchant(codeMerchant, statusMerchant);
        return "Update merchant status successfully";
    }

    @GetMapping(value = "/merchantOpen", produces = "application/json")
    public List<MerchantResponse> merchantOpen(){
        return merchantService.showMerchantOpen();
    }

    @GetMapping(value = "/merchantDetail")
    @Operation(summary = "Getting detail of one merchant by merchant name")
    public ResponseEntity getMerchantDetail(@RequestParam("merchantName") String merchantName){
        MerchantResponse merchantResponse = merchantService.getMerchantDetail(merchantName);
        if (Objects.nonNull(merchantResponse)) {
            return new ResponseEntity<>(Response.builder()
                    .Data(merchantService.getMerchantDetail(merchantName))
                    .isSuccess(Boolean.TRUE)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .Error(ErrorResponse.builder()
                        .errorMessage("Merchant with name " + merchantName + " not found")
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build())
                .Data(null)
                .isSuccess(Boolean.FALSE)
                .build(), HttpStatus.NOT_FOUND);
    }
}