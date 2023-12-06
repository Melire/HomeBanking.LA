package com.lamlvbank.homebanking.model.mapper;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {
//? Mapper que convierte de Entity a DTO.
    public AccountDTO accountToDto(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountN(account.getAccountN());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setBalance(account.getBalance());
        dto.setIdAT(account.getAccountType().getIdAT());
        dto.setIdC(account.getCurrency().getIdC());
        dto.setIdU(account.getUser().getIdU());
    return dto;    
 //* Construir DTO con BUILDER de lombok.
//? Opción 1.
        // return AccountDTO.builder()
        //             .accountN(account.getAccountN())
        //             .cbu(account.getCbu())
        //             .alias(account.getAlias())
        //             .balance(account.getBalance())
        //             .idT(account.getAccountType().getIdAT())
        //             .idC(account.getCurrency().getIdC())
        //             .build();
//? Opción 1.
        // AccountDTO dto =  AccountDTO.builder()
        //             .accountN(account.getAccountN())
        //             .cbu(account.getCbu())
        //             .alias(account.getAlias())
        //             .balance(account.getBalance())
        //             .idT(account.getAccountType().getIdAT())
        //             .idC(account.getCurrency().getIdC())
        //             .build();         
        // return dto;
    }

//? Mapper que convierte de DTO a Entity.
    public Account dtoToAccount(AccountDTO dto) {
        Account account = new Account();
        account.setAccountN(dto.getAccountN());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setBalance(dto.getBalance());
        account.addType(dto.getIdAT());
        account.addCurrency(dto.getIdC());
        account.addUser(dto.getIdU());
        return account;
    }
}
