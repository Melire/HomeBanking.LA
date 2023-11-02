package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    public List<Account> findAll();

    public Optional<Account> findById(Long idA);

    public Account save(Account account);

    public boolean deleteById(Long idA);
   

}