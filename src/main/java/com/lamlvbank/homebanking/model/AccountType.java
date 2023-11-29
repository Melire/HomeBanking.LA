package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idAT")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAT;

    @NotNull @NotBlank @Size(min = 8, max = 40)
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^Account\\s([A-Za-z]+)$", message = "Patron erroneo, debe ser Account [Palabra]")
    //@Length(min=8, max=40) combina @Notblank y @Size
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "accountType")
    private List<Account> account;

}
