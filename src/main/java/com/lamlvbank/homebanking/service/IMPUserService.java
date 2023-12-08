package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.User;
import com.lamlvbank.homebanking.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IMPUserService implements UserService {
    private UserRepository userTR;
    private AccountService accSer;
    private PasswordEncoder passEnc;

    @Autowired
    private EntityManager em;

    public IMPUserService(UserRepository userRep, AccountService accSer) {
        this.userTR = userRep;
        this.accSer = accSer;
        this.passEnc = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAll() {
        return userTR.findAll();
    }

    @Override
    public Optional<User> findByDni(String dniU) {
        Optional<User> user = userTR.findByDni(dniU);
    return user;
    }

    @Override
    public Optional<User> findById(Long idU) {
        Optional<User> user = userTR.findById(idU);
        return user;
    }

    @Override
    public User save(User user) {
        if (userTR.existsByDni(user.getDni())) {
            return user;
        }
        user.setCreationDate(LocalDateTime.now());
        user.setLastModifyDate(LocalDateTime.now());
        user.setPassword(this.passEnc.encode(user.getPassword()));
    return userTR.save(user);
    }

    @Override
    public User register(User user) {
        Account account = accSer.generateAccount(1L,1L,0L);
        user.setPassword(passEnc.encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        user.setLastModifyDate(LocalDateTime.now());
        account.setUser(user);
        user.getAccounts().add(account);
    return userTR.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        Optional<User> userToUpdate = userTR.findByDni(user.getDni());
        if (userToUpdate.isPresent()){
            userToUpdate.get().setName((user.getName() != null)?
                user.getName() : userToUpdate.get().getName());
            userToUpdate.get().setSurname((user.getSurname() != null)?
                user.getSurname() : userToUpdate.get().getSurname());
            userToUpdate.get().setPassword(
                (passEnc.matches(user.getPassword(),userToUpdate.get().getPassword())) ?
                    passEnc.encode(user.getPassword()) : userToUpdate.get().getPassword()
                    );
            userToUpdate.get().setBirthdate((user.getBirthdate() != null)?
                                user.getBirthdate() : userToUpdate.get().getBirthdate());
//!         userToUpdate.get().setLastModifyDate(LocalDateTime.now());  Dato actualizado por trigger.

           userTR.save(userToUpdate.get());
           userToUpdate = userTR.findByDni(user.getDni());
           em.refresh(userToUpdate.get());
        return userToUpdate.orElseThrow();
        }
    return user;
    }

    @Override
    public boolean deleteById(Long idU) {
        if (userTR.existsById(idU)) {
            userTR.deleteById(idU);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteByDni(String dniU) {
        if (userTR.existsByDni(dniU)) {
            userTR.deleteByDni(dniU);
            return true;
        }
        return false;
    }
}