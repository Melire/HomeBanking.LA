package com.lamlvbank.homebanking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idT;

    @NotNull @Min(1) @Max(999999999)
    @Column(nullable = false, unique = true)
    private Long transN;

    @NotNull @DecimalMin("1.0") @DecimalMax("1000000.0")
    @Column(nullable = false)
    private float amount;
}
