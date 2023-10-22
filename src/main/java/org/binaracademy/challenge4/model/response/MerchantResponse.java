package org.binaracademy.challenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponse {
    private String merchantCode;
    private String merchantName;
    private String merchantLocation;
    private Boolean merchantOpen;
}
