package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Transaction;
import com.lamlvbank.homebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMPTransactionService implements TransactionService {
    @Autowired
    private TransactionRepository tR;


    @Override
    public List<Transaction> findAll() {
        return tR.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long idT) {
        return tR.findById(idT);
    }

    @Override
    public Transaction save(Transaction trans) {
        if(tR.existsByTransN(trans.getTransN())){
            return null;
        }
        return tR.save(trans);
    }

    @Override
    public boolean deleteById(Long idT) {
        if(tR.existsById(idT)){
            tR.deleteById(idT);
            return true;
        }
        return false;
    }
}
