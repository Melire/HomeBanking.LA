package com.lamlvbank.homebanking.controller;

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
    private TransferenceService transferServ;

    @GetMapping
    ResponseEntity<List<Transference>>findAll(){
        return ResponseEntity.ok(transferServ.findAll());
    }

    @GetMapping("/origin/{idO}")
    ResponseEntity<List<Transference>>findAllByOrigin(@PathVariable("idO")Long idO){
        return ResponseEntity.ok(transferServ.findAllByOrigin(idO));
    }

    @GetMapping("/destiny/{idD}")
    ResponseEntity<List<Transference>>findAllByDestiny(@PathVariable("idD")Long idD){
        return ResponseEntity.ok(transferServ.findAllByDestiny(idD));
    }

    @GetMapping("/{idTr}")
    ResponseEntity<Transference>findById(@PathVariable("idTr")Long idTr){
        Optional<Transference>optTransf=transferServ.findById(idTr);
        if (optTransf.isPresent()) {
            return ResponseEntity.ok(optTransf.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<Transference> save(@Valid @RequestBody Transference transference){
        Transference transferenceSaved = transferServ.save(transference);
        if (transferenceSaved != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transferenceSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    ResponseEntity<Transference> register(@Valid @RequestBody TransferenceDTO dto){
        Transference transferenceSaved = transferServ.register(dto);
        if (transferenceSaved  != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(transferenceSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idT}")
    ResponseEntity<?> deleteById(@PathVariable("idT") Long idT) {
        if (transferServ.deleteById(idT)) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
