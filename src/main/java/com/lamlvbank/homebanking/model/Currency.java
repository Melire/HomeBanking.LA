package com.lamlvbank.homebanking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "currency")
    @JsonBackReference
    private List<Account> accounts;

    /* @OneToMany(mappedBy = "Currency")
    private List<Franlocura> Franlocura; */

}
