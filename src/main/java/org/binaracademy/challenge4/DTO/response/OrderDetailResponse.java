package org.binaracademy.challenge4.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    private String username;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private Double totalPrice;
    private OrderResponse orderResponse;
}