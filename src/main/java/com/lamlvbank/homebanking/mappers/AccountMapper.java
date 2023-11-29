package com.lamlvbank.homebanking.mappers;

import com.lamlvbank.homebanking.model.Account;

import com.lamlvbank.homebanking.model.dtos.AccountDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    // Metodo para pasar de Account a Dto
    public AccountDto accountToDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setAccountN(account.getAccountN());
        // dto.setType(account.getType()); falta habilitar el AccountType
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setBalance(account.getBalance());

        return dto;

    }

    // Metodo para pasar de Dto a Account
    public Account dtoToAccount(AccountDto dto) {
        Account account = new Account();
        account.setAccountN(dto.getAccountN());
        // account.setType(dto.getType()); falta habilitar el AccountType
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setBalance(dto.getBalance());
        /*
         * SE MODIFICA CUANDO UNAMOS LAS BRANCH CON TODOS
         * Se usan metodos creados para facilitar la union de Currency y de AccountType
         * AccountType accType = new AccountType();
         * 
         * accType.setIdAT(dto.getIdAT);
         * account.setAccountType(accType);
         */
        return account;
    }

}
