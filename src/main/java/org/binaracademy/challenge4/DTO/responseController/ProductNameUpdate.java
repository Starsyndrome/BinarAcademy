package org.binaracademy.challenge4.DTO.responseController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductNameUpdate {

    private String productCode;
    private String newProductName;
    private String info;
}