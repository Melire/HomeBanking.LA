
package com.lamlvbank.homebanking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //? Se aplica funcionalidad de patron de diseño
public class AccountDTO {
    private String accountN;
    private String cbu;
    private String alias;
    private float balance;
    private Long idAT;
    private Long idC;
    private Long idU;
}
