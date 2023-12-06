package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

/* Comente el codigo anterior para cambiar el List y poder implementar el uso de Dtos */

public interface AccountService {

    /* public List<Account> findAll(); */

    public List<AccountDTO> findAll();

    /* public Optional<Account> findById(Long idA); */

    public Optional<AccountDTO> findById(Long idA);

    public Account save(Account account);

    public AccountDTO openAccount(AccountDTO dto);

    public boolean deleteById(Long idA);

    public Account update(Account account);


    public Account generateAccount();

}