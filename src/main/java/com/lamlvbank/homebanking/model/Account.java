package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idA")
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

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
    private List<Transaction> transactions;


    // GETTERS Y SETTERS

    // public Long getIdA() {
    // return idA;
    // }
    //
    // public void setIdA(Long idA) {
    // this.idA = idA;
    // }
    //
    // public String getAccountN() {
    // return accountN;
    // }
    //
    // public void setAccountN(String accountN) {
    // this.accountN = accountN;
    // }
    //
    // public String getCbu() {
    // return cbu;
    // }
    //
    // public void setCbu(String cbu) {
    // this.cbu = cbu;
    // }
    //
    // public String getAlias() {
    // return alias;
    // }
    //
    // public void setAlias(String alias) {
    // this.alias = alias;
    // }
    //
    // public float getBalance() {
    // return balance;
    // }
    //
    // public void setBalance(float balance) {
    // this.balance = balance;
    // }
}
