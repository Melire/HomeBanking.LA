package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Entity
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private AccountType accountType;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    private List<Transference> transferences;

    public Account(){
        this.transactions = new ArrayList<>();
        this.transferences = new ArrayList<>();
    }

    public void addType(Long idAT) {
        AccountType accType = new AccountType();
        accType.setIdAT(idAT);
        this.setAccountType(accType);
    }
}
