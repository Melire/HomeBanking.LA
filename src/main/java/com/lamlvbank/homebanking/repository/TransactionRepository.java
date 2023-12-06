package com.lamlvbank.homebanking.repository;

import com.lamlvbank.homebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsByTransN(long transN);
    Optional<Transaction> findByTransN(long transN);
}
