package org.binaracademy.challenge4.DTO.responseController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOpenUpdate {

    private Boolean status;
    private String merchantCode;
    private String merchantStatus;
}
