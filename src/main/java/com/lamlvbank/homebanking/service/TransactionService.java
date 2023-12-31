package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAll();
    Optional<Transaction> findById(Long idT);
    Transaction save(Transaction trans);
    Transaction update(Transaction trans);
    boolean deleteById(Long idT);
}
