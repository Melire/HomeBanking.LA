package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
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

//? '@Patter' se usa para definir un patron o forma especifica en la que se compone el String
//? Utiliza expresiones regulares para lograrlo(regexp),para este caso,toma 3 fundamentos:
//?     *Son 3 palabras.//Cada una de ellas solo en minúscula.//Separadas por un '.'
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-z]+\\.[a-z]+\\.[a-z]+$", message = "Debe ser 3 palabras en minúsculas separadas por puntos")
    @Column(unique = true, nullable = false)
    private String alias;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20000000.0")
    private float balance;

//? 'creationDate' y 'lastModifyDate' entran en juego para registrar los cambios de la entidad.
//? '@JsonFormat' declara un patron/formato para recuperar los datos de la BDD.
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastModifyDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("accounts")
    private User user;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("account")
    private List<Transference> transferences;

    public Account() {
        this.transactions = new ArrayList<>();
        this.transferences = new ArrayList<>();
    }

    public void addCurrency(Long idC) {
        Currency currency = new Currency();
        currency.setIdC(idC);
        this.setCurrency(currency);
    }

    public void addType(Long idAT) {
        AccountType accType = new AccountType();
        accType.setIdAT(idAT);
        this.setAccountType(accType);
    }

    public void addUser(Long idU) {
        User userAdd = new User();
        userAdd.setIdU(idU);
        this.setUser(userAdd);
    }
}
