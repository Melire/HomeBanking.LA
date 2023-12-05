package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;
import com.lamlvbank.homebanking.repository.AccountRepository;
import com.lamlvbank.homebanking.repository.TransferenceRepository;
import com.lamlvbank.homebanking.tool.exception.*;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ImplAccountService implements AccountService {
//? Mediante Inyección de dependencia declaramos un Repository,...
//? ... manteniendo un bajo acoplamiento y cohesion.
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransferenceRepository tR;

    @Autowired
    private EntityManager em;

//? Utilizamos lambda para poder rellenar las transferencias de aquellas cuentas que son 'DESTINY'.  
//? Ya que la relación establecida en 'Transference' mapea a una sola propiedad('ORIGIN').
    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepo.findAll();    
        accounts.forEach(account -> account.getTransferences()
                        .addAll(tR.findAllByDestinyIdA(account.getIdA())));
        return accounts;
    }

    @Override
    public Optional<Account> findById(Long idA) {
        return accountRepo.findById(idA);
    }

    @Override
    public Account save(Account account) {
        if (!(accountRepo.existsByAccountN(account.getAccountN())) 
                && !(accountRepo.existsByAlias(account.getAlias()))
                && !(accountRepo.existsByCbu(account.getCbu()))) {
                    account.setCreationDate(LocalDateTime.now());
                    account.setLastModifyDate(LocalDateTime.now());
            return accountRepo.save(account);
        } 
    return account;
    }

//? Método dedicado a la apertura de una Cuenta nueva de un usuario existente.
//? Toda la información de la cuenta es generada por los 'Métodos de Soporte'.
//? A excepción de las relaciones con Entidades, se lleva a cabo mediante los 'add'.
    @Override
    public Account openAccount(AccountDTO dto) {
        Account account = generateAccount(dto.getIdAT(),dto.getIdC(),dto.getIdU());
        if(account.getUser() != null){
            return accountRepo.save(account);
        }
        return account;
    }

//! Disponible solo para cambiar atributo 'alias'.    
    @Override
    @Transactional
    public Account update(Account account) {
        Optional<Account> accountToUpdate = accountRepo.findByAccountNAndCbu(account.getAccountN()
                                                                            ,account.getCbu());
        if (accountToUpdate.isPresent()) {
            accountToUpdate.get().setAlias(account.getAlias());
//!         accountToUpdate.get().setLastModifyDate(LocalDateTime.now()); Dato actualizado por trigger.
            accountRepo.save(accountToUpdate.get());
            accountToUpdate = accountRepo.findByAccountNAndCbu(account.getAccountN(),account.getCbu());
            em.refresh(accountToUpdate.orElseThrow());
        return accountToUpdate.orElseThrow();
        }
    return account;
    }


    @Override
    public boolean deleteById(Long idA) {
        if (accountRepo.existsById(idA)) {
            accountRepo.deleteById(idA);
            return true;
        }
        return false;
    }

//? Métodos de Soporte. Limitada a la capa SERVICE, sin contacto con los Controllers.
    @Override
    public Account generateAccount(Long idAT, Long idC,Long idU) {
        Account account = new Account();
        account.setAccountN(genAccNumber());
        account.setCbu(genCBU());
        account.setAlias(genAlias());
        account.setBalance(0f);
        account.setCreationDate(LocalDateTime.now());
        account.setLastModifyDate(LocalDateTime.now());
        account.addType(idAT);
        account.addCurrency(idC);
        if(accountRepo.existsByUserIdU(idU)){
            account.addUser(idU);
        }
    return account;
    }
    
//! Los generadores jamas soltaran un valor que ya este almacenado en la BDD, son ÚNICOS.    
//? Generador de Numero de Cuenta RANDOM.
    private String genAccNumber() {
        Long accNumberAux = 0L;
        Random random = new Random();
        String accNumber = "";
        do {
            accNumberAux = Math.abs(random.nextLong() % (999999999 + 1));
            accNumber = String.valueOf(accNumberAux);
        } while (accountRepo.existsByAccountN(accNumber));
        return accNumber;
    }

//? Generador de CBU RANDOM.
    private String genCBU() {
        Long accCBUAux = 0L;
        Random random = new Random();
        String accCBU = "";
        do {
            accCBUAux = Math.abs(random.nextLong() % (Long.MAX_VALUE + 1));
            accCBU = String.valueOf(accCBUAux);
        } while (accountRepo.existsByAccountN(accCBU));
        return accCBU;
    }

//? Generador de Alias RANDOM.
    private String genAlias() {
        String[] words = { "gadget", "mecanico", "tio", "chucheria", "densa", "opinar", "amigos", "cosmetico",
                "delicadeza", "energia", "dos", "vena", "camaleon", "atrevida", "condenacion", "libro",
                "mago", "recepcion", "luchar", "cashbox", "atornillar", "desafio", "volar",
                "juego", "sadden", "incompetente", "desprendible", "deporte", "beneficioso",
                "corporacion"};
        String alias = null;
        Random random = new Random();

        do {
            alias = IntStream.range(0, 3)// Recorre 3 veces el array
                    .mapToObj(word -> words[random.nextInt(words.length)])// obtiene la palabra de words por cada
                                                                          // reccorido
                    .collect(Collectors.joining("."));// Toma la palabra y lo junta al string (alias)
        } while (accountRepo.existsByAlias(alias));
    return alias;
    }

    
//? Método encargado de la actualización de montos durante una operación 'Transference'.
//! En caso de no pasar el primer if, 'OriginOrDestinyNotFoundException' forzara una excepción.
//! En caso de no pasar el segundo if, 'InsufficientBalanceException' forzara una excepción.
//!! En ambos casos, trabajaran conjunto al ServiceExceptionHandler para llevar una respuestas ...
//!! ... declarativas hacia afuera de la api,tomando un 'atajo' fuera de ella.
    @Override
    public void updateAmounts(Long idO, Long idD,float amount){
        Optional<Account> originAcc = accountRepo.findById(idO);
        Optional<Account> destinyAcc = accountRepo.findById(idD);

        if(originAcc.isPresent() && destinyAcc.isPresent()){
            if(originAcc.get().getBalance() >= amount){
                originAcc.get().setBalance(originAcc.get().getBalance() - amount);
                destinyAcc.get().setBalance(destinyAcc.get().getBalance() + amount);
//!             originAcc.get().setLastModifyDate(LocalDateTime.now()); Dato actualizado por trigger.
//!             destinyAcc.get().setLastModifyDate(LocalDateTime.now()); Dato actualizado por trigger.
                accountRepo.save(originAcc.get());
                accountRepo.save(destinyAcc.get());
            }else{
                throw new InsufficientBalanceException("The origin account does not have sufficient balance to cover the operation.");
            }
        }else{
            throw new OriginOrDestinyNotFoundException("One of the accounts involved in the operation is not available.");
        }
    }
}

