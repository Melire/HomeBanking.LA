package com.lamlvbank.homebanking.Model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idC;
    private String name;
}
