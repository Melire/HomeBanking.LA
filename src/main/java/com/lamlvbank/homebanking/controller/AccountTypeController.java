package com.lamlvbank.homebanking.controller;

import com.lamlvbank.homebanking.model.AccountType;
import com.lamlvbank.homebanking.service.AccountTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/accTypes")
public class AccountTypeController {
    @Autowired
    private AccountTypeService aTS;

    @GetMapping
    ResponseEntity<List<AccountType>> findAll() {
        return ResponseEntity.ok(aTS.findAll());
    }

    @GetMapping("/{idAT}")
    ResponseEntity<AccountType> findById(@PathVariable("idAT") Long idAT) {
        Optional<AccountType> optAccountType = aTS.findById(idAT);
        if (optAccountType.isPresent()) {
            return ResponseEntity.ok(optAccountType.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<AccountType> save(@Valid @RequestBody AccountType accountType) {
        AccountType accTypeSaved = aTS.save(accountType);
        if (accTypeSaved!= null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accTypeSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    @DeleteMapping ("/{idAT}")
    ResponseEntity<?> deleteById (@PathVariable("idAT") Long idAT){
        if (aTS.deleteById(idAT)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
