
package com.lamlvbank.homebanking.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Se aplica funcionalidad de patron de diseño
public class AccountDto {
    private String accountN;
    private String cbu;
    private String alias;
    private float balance;
    private Long idT;
    private Long idC;
}