package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplAccountService implements AccountService {

    //Inyeccion de dependencia del Repo
    @Autowired
    private AccountRepository accountRepo;


    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<Account> findById(Long idA) {
        return accountRepo.findById(idA);
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public boolean deleteById(Long idA) {
        accountRepo.deleteById(idA);
        if (accountRepo.existsById(idA)) {
            return false;

        }
        return true;

    }
}
