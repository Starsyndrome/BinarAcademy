package org.binaracademy.challenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String productName;
    private Integer productPrice;
    private Integer quantity;
    private Double totalPrice;
}