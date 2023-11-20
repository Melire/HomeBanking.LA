package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ImplAccountService implements AccountService {

    // Inyeccion de dependencia del Repo
    @Autowired
    private AccountRepository accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<Account> findById(Long idA) {
        return accountRepo.findById(idA);
    }

    @Override
    public Account save(Account account) {
        if (!(accountRepo.existsByAccountN(account.getAccountN())) && !(accountRepo.existsByAlias(account.getAlias()))
                && !(accountRepo.existsByCbu(account.getCbu()))) {
            return accountRepo.save(account);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long idA) {
        if (accountRepo.existsById(idA)) {
            accountRepo.deleteById(idA);
            return true;
        }
        return false;

    }

    @Override
    public Account update(Account account) {
        Optional<Account> accountToUpdate = accountRepo.findByAccountN(account.getAccountN());
        if (accountToUpdate.isPresent()) {

            accountToUpdate.get().setAlias(account.getAlias());
            accountToUpdate.get().setBalance(account.getBalance());

            Account accountUpdated = accountRepo.save(accountToUpdate.get());
            return accountUpdated;
        }
        return account;
    }

//Validar que el cbu y al numero de cuenta pertenezca a la misma entidad



    @Override
    public Account generateAccount() {
        Account account = new Account();
        account.setAccountN(genAccNumber());
        account.setCbu(genCBU());
        account.setAlias(genAlias());
        account.setBalance(0f);
        return account;
    }

    private String genAccNumber(){
        Long accNumberAux = 0L;
        Random random = new Random();
        String accNumber = "";
        do{
            accNumberAux = Math.abs(random.nextLong() % (999999999 + 1));
            accNumber = String.valueOf(accNumberAux);
        }while(accountRepo.existsByAccountN(accNumber));
        return accNumber;
    }

    private String genCBU(){
        Long accCBUAux = 0L;
        Random random = new Random();
        String accCBU = "";
        do{
            accCBUAux = Math.abs(random.nextLong() % (Long.MAX_VALUE + 1));
            accCBU = String.valueOf(accCBUAux);
        }while(accountRepo.existsByAccountN(accCBU));
        return accCBU;
    }

    private String genAlias() {
      String[] words  = {"gadget", "mecanico", "tio", "chucheria", "densa", "opinar", "amigos", "cosmetico",
                         "delicadeza", "energia", "dos", "vena", "camaleon", "atrevida", "condenacion", "libro",
                        "mago", "recepcion", "luchar", "cashbox", "atornillar", "desafio", "violar",
                        "juego", "sadden", "incompetente", "desprendible", "deporte", "beneficioso",
                        "corporacion"};
       String alias = null;
       Random random = new Random();

       do{
           alias = IntStream.range(0,3)
                   .mapToObj(word -> words[random.nextInt(words.length)])
                   .collect(Collectors.joining("."));
       } while(accountRepo.existsByAlias(alias));
       return alias;
    }


}

