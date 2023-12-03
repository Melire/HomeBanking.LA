package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> findAll();
    public Optional<Account> findById(Long idA);
    public Account save(Account account);
    public AccountDTO openAccount(AccountDTO dto);
    public boolean deleteById(Long idA);
    public Account update(Account account);
    public void updateAmounts(Long idO,Long idD,float amount);

    public Account generateAccount(Long idAT,Long idC,Long idU);
}