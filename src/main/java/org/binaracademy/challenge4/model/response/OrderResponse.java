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
    // Ini jugaa gatau kak bener apa ngga :((((
    private String productName;
    private Integer productPrice;
    private Integer quantity;
    private Integer totalPrice;
}