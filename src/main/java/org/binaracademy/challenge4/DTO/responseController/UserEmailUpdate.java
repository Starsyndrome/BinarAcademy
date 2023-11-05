package org.binaracademy.challenge4.DTO.responseController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailUpdate {

    private String username;
    private String email;
    private String info;
}