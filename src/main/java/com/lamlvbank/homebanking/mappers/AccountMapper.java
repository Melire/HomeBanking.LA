package com.lamlvbank.homebanking.mappers;

import com.lamlvbank.homebanking.model.Account;

import com.lamlvbank.homebanking.model.dto.AccountDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    // Metodo para pasar de Account a Dto
    public AccountDTO accountToDto(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountN(account.getAccountN());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setBalance(account.getBalance());
        dto.setIdT(account.getAccountType().getIdAT());
        dto.setIdC(account.getCurrency().getIdC());

        return dto;

    }

    // Metodo para pasar de Dto a Account
    public Account dtoToAccount(AccountDTO dto) {
        Account account = new Account();
        account.setAccountN(dto.getAccountN());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setBalance(dto.getBalance());
        account.addType(dto.getIdT());
        account.addCurrency(dto.getIdC());
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
