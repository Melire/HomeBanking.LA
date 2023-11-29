package com.lamlvbank.homebanking.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idA")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idA;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 11)
    @Column(unique = true, nullable = false)
    private String accountN;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 22)
    @Column(unique = true, nullable = false)
    private String cbu;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-z]+\\.[a-z]+\\.[a-z]+$", message = "Debe ser 3 palabras en minúsculas separadas por puntos")
    @Column(unique = true, nullable = false)
    private String alias;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20000000.0")
    private float balance;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.EAGER)
    private AccountType accountType;

    public void addType(Long idAT) {
        AccountType accType = new AccountType();
        accType.setIdAT(idAT);
        this.setAccountType(accType);
    }
}
