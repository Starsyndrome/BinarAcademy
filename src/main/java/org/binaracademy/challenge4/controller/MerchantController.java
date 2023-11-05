package org.binaracademy.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.DTO.response.ErrorResponse;
import org.binaracademy.challenge4.DTO.response.MerchantResponse;
import org.binaracademy.challenge4.DTO.responseController.MerchantOpenUpdate;
import org.binaracademy.challenge4.DTO.response.Response;
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
    public ResponseEntity<String> addNewMerchant(@RequestBody Merchant merchant){
        merchantService.addNewMerchant(merchant);
        return ResponseEntity.ok()
                .body("Add new merchant with merchant name: " + merchant.getMerchantName() + " successfully!");
    }

    @PutMapping(value = "/update/statusMerchant/{codeMerchant}")
    public ResponseEntity<MerchantOpenUpdate> updateStatusMerchant(@RequestParam("newStatusMerchant") Boolean newStatusMerchant,
                                       @PathVariable("codeMerchant") String codeMerchant,
                                       @RequestBody Merchant merchant){
        merchantService.editStatusMerchant(codeMerchant, newStatusMerchant);
        return ResponseEntity.ok()
                .body(MerchantOpenUpdate.builder()
                        .merchantCode(codeMerchant)
                        .status(newStatusMerchant)
                        .merchantStatus("Merchant status successfully updated!")
                .build());
    }

    @GetMapping(value = "/merchantOpen", produces = "application/json")
    public ResponseEntity<List<MerchantResponse>> merchantOpen(){
        return ResponseEntity.ok()
                .body(merchantService.showMerchantOpen());
    }

    @GetMapping(value = "/merchantDetail")
    @Operation(summary = "Getting detail of one merchant by merchant name")
    public ResponseEntity<Response<Object>> getMerchantDetail(@RequestParam("merchantName") String merchantName){
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