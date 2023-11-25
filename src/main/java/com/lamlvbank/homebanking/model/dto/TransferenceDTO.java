package com.lamlvbank.homebanking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenceDTO {
    private Long idO;
    private Long idD;
    private float amount;
    private String reference;
}
