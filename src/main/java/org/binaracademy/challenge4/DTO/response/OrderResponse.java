package org.binaracademy.challenge4.DTO.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date orderTime;

    private String destinationAddress;
    private Boolean completed;
}