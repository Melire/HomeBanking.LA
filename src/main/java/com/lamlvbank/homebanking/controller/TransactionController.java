package com.lamlvbank.homebanking.controller;

import com.lamlvbank.homebanking.model.Transaction;
import com.lamlvbank.homebanking.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/transactions")
public class TransactionController {
    @Autowired
    private TransactionService tS;

    @GetMapping
    ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(tS.findAll());
    }

    @GetMapping("/{idT}")
    ResponseEntity<Transaction> findById(@PathVariable("idT")Long idT){
        Optional<Transaction> optTransaction=tS.findById(idT);
        if (optTransaction.isPresent()){
            return ResponseEntity.ok(optTransaction.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Transaction> save(@Valid @RequestBody Transaction transaction){
        Transaction transactionSaved = tS.save(transaction);
        if (transactionSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    ResponseEntity<Transaction> update(@Valid @RequestBody Transaction transaction){
        Transaction transactionUpdated = tS.update(transaction);
        if (transactionUpdated.getIdT() != null){
            return ResponseEntity.ok(transactionUpdated);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idT}")
    ResponseEntity<?> deleteById(@PathVariable("idT") Long idT) {
        if (tS.deleteById(idT)) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
