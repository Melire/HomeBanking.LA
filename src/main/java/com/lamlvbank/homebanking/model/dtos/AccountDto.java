
package com.lamlvbank.homebanking.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    /*
     * private Long idT;
     */
    private String accountN;

    private String cbu;

    private String alias;

    private float balance;
}