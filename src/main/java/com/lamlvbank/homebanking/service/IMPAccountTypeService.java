package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.AccountType;
import com.lamlvbank.homebanking.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class IMPAccountTypeService implements AccountTypeService {
    @Autowired
    private AccountTypeRepository accTR;

    @Override
    public List<AccountType> findAll() {
        return accTR.findAll();
    }

    @Override
    public Optional<AccountType> findById(Long idAt) {
        return accTR.findById(idAt);
    }

    @Override
    public AccountType save(AccountType accType) {
        if (accTR.existsByName(accType.getName())){
            return null;
        }
        accType.setCreationDate(LocalDateTime.now());
        return accTR.save(accType);
    }
    
    @Override
    public AccountType update(AccountType accType) {
        if (accTR.existsById(accType.getIdAT())){
            return accTR.save(accType);
        }
        accType.setIdAT(0L);
        return accType;
    }

    @Override
    public boolean deleteById(Long idAt) {
        if(accTR.existsById(idAt)){
            accTR.deleteById(idAt);
            return true;
        }
        return false;
    }
}
