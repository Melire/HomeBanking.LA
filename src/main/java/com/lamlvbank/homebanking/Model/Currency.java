package com.lamlvbank.homebanking.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @NonNull @NotBlank @Size(min=3, max=15)
    //@Length(min=3, max=40) combina funciones de @NotBlank y @Size
    @Column(nullable = false, unique = true)
    private String name;
}
