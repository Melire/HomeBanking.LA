package com.lamlvbank.homebanking.model.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferenceDTO {
    @NotNull @Min(1)
    private Long idO;

    @NotNull @Min(1)
    private Long idD;

    @NotNull @DecimalMin("1.0") @DecimalMax("1000000.0")
    private float amount;

    @NotNull @Length(min=10, max=40)
    private String reference;
}
