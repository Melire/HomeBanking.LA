package com.lamlvbank.homebanking.controller;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;
import com.lamlvbank.homebanking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/accounts")
public class AccountController {
    @Autowired
    private AccountService accServ;

    //FIND ALL ACCOUNTS
    @GetMapping
    ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok().body(accServ.findAll());
    }

    //FIND ACCOUNT BY ID
    @GetMapping("/{idA}")
    ResponseEntity<Account> findById(@PathVariable("idA") Long idA) {
        Optional<Account> optAccount = accServ.findById(idA);
        if (optAccount.isPresent()) {
            return ResponseEntity.ok().body(optAccount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //CREATE ACCOUNT
    @PostMapping("/{idAT}/{idC}/{idU}")
    ResponseEntity<Account> save(@Valid @RequestBody Account account,@PathVariable("idC") Long idC
                                ,@PathVariable("idAT") Long idAT,@PathVariable("idU") Long idU) {
        account.addCurrency(idC);
        account.addType(idAT);
        account.addUser(idU);
        Account accSaved = accServ.save(account);
        if (accSaved.getIdA() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

//? Método dedicado a la apertura de una cuenta nueva para un usuario, enviado desde el front.
//? Se aplica DTO por 2 razones fundamentales:
//?     *Seguridad de los ID de las entidades INDISPENSABLES para la apertura de la cuenta.
//?     *Facilidad de organización de datos a enviar desde el FrontEnd.
    @PostMapping("/new")
    ResponseEntity<Account> openAccount(@Valid @RequestBody AccountDTO dto) {
        Account accSaved = accServ.openAccount(dto);
        if (accSaved.getIdA() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accSaved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //UPDATE ACCOUNT
//? Aplica solo para actualizar el ALIAS.
    @PutMapping
    ResponseEntity<Account> update(@Valid @RequestBody Account account) {
        Account accUpdated = accServ.update(account);
        if (accUpdated.getIdA() != null){
            return ResponseEntity.ok(accUpdated);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE ACCOUNT
    @DeleteMapping("/{idA}")
    ResponseEntity<?> deleteById(@PathVariable("idA") Long idA) {
        if (accServ.deleteById(idA)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
