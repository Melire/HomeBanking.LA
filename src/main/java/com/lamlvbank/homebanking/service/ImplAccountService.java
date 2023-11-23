package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImplAccountService implements AccountService {

    // Inyeccion de dependencia del Repo
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
//linea 34 Setee la hora en la que se creo la cuenta

    @Override
    public Account save(Account account) {
        if (!(accountRepo.existsByAccountN(account.getAccountN())) && !(accountRepo.existsByAlias(account.getAlias()))
                && !(accountRepo.existsByCbu(account.getCbu()))) {
                    account.setCreated_at(LocalDateTime.now());
            return accountRepo.save(account);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long idA) {
        if (accountRepo.existsById(idA)) {
            accountRepo.deleteById(idA);
            return true;
        }
        return false;

    }
//Linea 58 setee la hora en la que se modifico
    @Override
    public Account update(Account account) {
        Optional<Account> accountToUpdate = accountRepo.findByAccountN(account.getAccountN());
        if (accountToUpdate.isPresent()) {

            accountToUpdate.get().setAlias(account.getAlias());
            accountToUpdate.get().setBalance(account.getBalance());
            accountToUpdate.get().setUpdated_at(LocalDateTime.now());
            Account accountUpdated = accountRepo.save(accountToUpdate.get());
            return accountUpdated;
        }
        return account;
    }
//Validar que el cbu y al numero de cuenta pertenezca a la misma entidad
}

