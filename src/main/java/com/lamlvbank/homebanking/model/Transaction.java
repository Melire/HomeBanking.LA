package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idT")
@Inheritance(strategy = InheritanceType.JOINED)
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Account origin;
}
