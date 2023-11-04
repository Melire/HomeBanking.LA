package com.lamlvbank.homebanking.Controller;

import com.lamlvbank.homebanking.Model.Currency;
import com.lamlvbank.homebanking.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/apiHB/currency")

public class CurrencyController {

   @Autowired
   private CurrencyService Cs;
   @GetMapping
   ResponseEntity<List<Currency>> findAll() {
       return ResponseEntity.ok(Cs.findAll());
   }
}