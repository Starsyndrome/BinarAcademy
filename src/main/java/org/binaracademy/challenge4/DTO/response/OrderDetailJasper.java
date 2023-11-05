package org.binaracademy.challenge4.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailJasper {

    private String productName;
    private Long quantity;
    private String price;
}