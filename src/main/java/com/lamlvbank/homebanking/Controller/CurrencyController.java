package com.lamlvbank.homebanking.Controller;


import com.lamlvbank.homebanking.Model.Currency;
import com.lamlvbank.homebanking.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService cs;
    @GetMapping
    ResponseEntity<List<Currency>> findAll() {
        return ResponseEntity.ok(cs.findAll());
    }

    @GetMapping("/{idC}")
    ResponseEntity<Currency> findById(@PathVariable("idC")Long idC){
        Optional<Currency> optionalCurrency=cs.findById(idC);
        if (optionalCurrency.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(optionalCurrency.get());
        }
    }
}
