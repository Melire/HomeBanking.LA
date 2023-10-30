package com.lamlvbank.homebanking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.service.AccountService;

@RestController
@RequestMapping("/apiHB/accounts")
public class AccountController {
    @Autowired
    private AccountService accountServ;

    @GetMapping
    ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok().body(accountServ.findAll());
    }

    @GetMapping("/{idA}")
    ResponseEntity<Account> findById(@PathVariable("idA") Long idA) {
        Optional<Account> optAccount = accountServ.findById(idA);
        if (optAccount.isPresent()) {
            return ResponseEntity.ok().body(optAccount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Account> save(@RequestBody Account account) {

        Account accSaved = accountServ.save(account);
        if (accSaved != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{idA}")
    ResponseEntity<?> deleteById(@PathVariable("idA") Long idA) {
        if (accountServ.deleteById(idA)) {
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
             }
    }

}
