package com.lamlvbank.homebanking.controller;

import com.lamlvbank.homebanking.model.Transaction;
import com.lamlvbank.homebanking.model.Transference;
import com.lamlvbank.homebanking.model.dto.TransferenceDTO;
import com.lamlvbank.homebanking.service.TransferenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiHB/transferences")
public class TransferenceController {
    @Autowired
    private TransferenceService ts;

    @GetMapping
    ResponseEntity<List<Transference>>findAll(){
        return ResponseEntity.ok(ts.findAll());
    }

    @GetMapping("/{idTr}")
    ResponseEntity<Transference>findById(@PathVariable("idTr")Long idTr){
        Optional<Transference>optTransf=ts.findById(idTr);
        if (optTransf.isPresent()) {
            return ResponseEntity.ok(optTransf.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Transference> save(/*@Valid*/ @RequestBody Transference transference){
        System.out.println(transference);
        Transference transferenceSaved = ts.save(transference);
        if (transferenceSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transferenceSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    ResponseEntity<Transference> register(@RequestBody TransferenceDTO dto){
        Transference transferenceSaved = ts.register(dto);
        if (transferenceSaved  != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transferenceSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idT}")
    ResponseEntity<?> deleteById(@PathVariable("idT") Long idT) {
        if (ts.deleteById(idT)) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
