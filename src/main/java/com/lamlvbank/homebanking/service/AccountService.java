package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dtos.AccountDto;

import java.util.List;
import java.util.Optional;

/* Comente el codigo anterior para cambiar el List y poder implementar el uso de Dtos */

public interface AccountService {

    /* public List<Account> findAll(); */

    public List<AccountDto> findAll();

    /* public Optional<Account> findById(Long idA); */

    public Optional<AccountDto> findById(Long idA);

    public Account save(Account account);

    public AccountDto openAccount(AccountDto dto);

    public boolean deleteById(Long idA);

    public Account update(Account account);


    public Account generateAccount();

}