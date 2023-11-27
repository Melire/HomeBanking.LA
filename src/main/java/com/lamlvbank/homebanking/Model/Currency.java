package com.lamlvbank.homebanking.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

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

    /* @OneToMany(mappedBy = "Currency")
    private List<Franlocura> Franlocura; */

}
