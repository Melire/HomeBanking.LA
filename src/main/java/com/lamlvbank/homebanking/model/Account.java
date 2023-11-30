package com.lamlvbank.homebanking.model;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idA")
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

    // Agregue LocalDateTime Fecha de creacion y fecha de modificacion //ImplAccount
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createDT;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastModifyDT;

    // Implementacion de AccountType y de Currency
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    private AccountType accountType;

    @ManyToOne
    @JsonIgnoreProperties("account")
    private Currency currency;

    // Metodos para indexar (conectar a nivel Jpa) entidades
    public void addType(Long idAT) {
        AccountType accType = new AccountType();
        accType.setIdAT(idAT);
        this.setAccountType(accType);
    }

    public void addCurrency(Long idC) {
        Currency currency = new Currency();
        currency.setIdC(idC);
        this.setCurrency(currency);
    }
}
