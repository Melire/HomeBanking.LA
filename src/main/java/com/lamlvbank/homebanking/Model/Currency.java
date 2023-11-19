package com.lamlvbank.homebanking.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @Column(unique = true, nullable = false)
    @NotNull @Length(min = 3, max = 3)
    private String name;
}
/*
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAT;

    @NotNull  @NotBlank @Size(min=8, max=40)
    @Column(unique = true,nullable=false)
    @Pattern(regexp ="^Account\\s([A-Za-z]+)$",message="Patron erroneo, debe ser Account [Palabra]")
    //@Length(min=8, max=40) combina @Notblank y @Size
    private String name;

} */