package org.binaracademy.challenge4.DTO.responseController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceUpdate {

    private String productCode;
    private Double productPrice;
    private String info;
}