package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.mappers.AccountMapper;
import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.dto.AccountDTO;
import com.lamlvbank.homebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    /*
     * !Metodos viejos
     * 
     * @Override
     * public List<Account> findAll() {
     * return accountRepo.findAll();
     * }
     * 
     * @Override
     * public Optional<Account> findById(Long idA) {
     * return accountRepo.findById(idA);
     * }
     */
    @Override
    public List<AccountDTO> findAll() {
        List<Account> account = accountRepo.findAll();
        return account.stream().map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> findById(Long idA) {
        Optional<Account> optAccount = accountRepo.findById(idA);

        return Optional.of(AccountMapper.accountToDto(optAccount.get()));

    }
    // linea 57 Setee la fecha y hora actual en la que se creo la cuenta

    @Override
    public Account save(Account account) {
        if (!(accountRepo.existsByAccountN(account.getAccountN())) && !(accountRepo.existsByAlias(account.getAlias()))
                && !(accountRepo.existsByCbu(account.getCbu()))) {
            account.setCreateDT(LocalDateTime.now());
            account.setLastModifyDT(LocalDateTime.now());
            return accountRepo.save(account);
        } else {
            return null;
        }
    }

    // REGISTER
    @Override
    public AccountDTO openAccount(AccountDTO dto) {
        Account account = generateAccount();
        account.addType(dto.getIdT());// Agregado de la rama de Meli
        account.addCurrency(dto.getIdC());// Agregado de la rama de Pedro
        dto = AccountMapper.accountToDto(accountRepo.save(account));
        return dto;
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
            accountToUpdate.get().setLastModifyDT(LocalDateTime.now());// guarda la fecha y hora actual de modificación
            Account accountUpdated = accountRepo.save(accountToUpdate.get());
            return accountUpdated;
        }
        return account;
    }
    // Validar que el cbu y al numero de cuenta pertenezca a la misma entidad

    @Override
    public Account generateAccount() {
        Account account = new Account();
        account.setAccountN(genAccNumber());
        account.setCbu(genCBU());
        account.setAlias(genAlias());
        account.setBalance(0f);
        account.setCreateDT(LocalDateTime.now());
        account.setLastModifyDT(LocalDateTime.now());
        return account;
    }

    // Genera numero random para el accountN
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

    private String genAlias() {
        String[] words = { "gadget", "mecanico", "tio", "chucheria", "densa", "opinar", "amigos", "cosmetico",
                "delicadeza", "energia", "dos", "vena", "camaleon", "atrevida", "condenacion", "libro",
                "mago", "recepcion", "luchar", "cashbox", "atornillar", "desafio", "volar",
                "juego", "sadden", "incompetente", "desprendible", "deporte", "beneficioso",
                "corporacion" };
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
}
