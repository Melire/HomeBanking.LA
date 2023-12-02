package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.AccountType;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {
    List <AccountType> findAll();
    Optional<AccountType> findById(Long idAt);
    AccountType save(AccountType accType);
    // AccountType update(AccountType accType);
    boolean deleteById(Long idAt);
}
