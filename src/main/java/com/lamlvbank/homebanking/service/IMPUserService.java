package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.User;
import com.lamlvbank.homebanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class IMPUserService implements UserService{
    @Autowired
    private UserRepository userTR;

    @Autowired
    private AccountService accSer;

    @Override
    public List<User> findAll() {
        return userTR.findAll();
    }

    @Override
    public Optional<User> findByDni(String dniU){
        return userTR.findByDni(dniU);
    }
    @Override
    public Optional<User> findById(Long idU) {
        return userTR.findById(idU);
    }

    @Override
    public User save(User user) {
        if(userTR.existsByDni(user.getDni())){
            return null;
        }
        user.setCreationDate(LocalDateTime.now());
        user.setLastModifyDate(LocalDateTime.now());
        return userTR.save(user);
    }
/*
generar usuario (ser recibe por parametros)
generar cuenta
indexar ambas entidades (asociar cuenta con usuario y usuario con cuenta)
 */
    @Override
    public User register(User user) {
         Account account = accSer.generateAccount();
         account.setUser(user);
         user.getAccounts().add(account);
         return userTR.save(user);
    }

    @Override
    public User update(User user){
        Optional <User> userToUpdate = userTR.findByDni(user.getDni());
        if(userToUpdate.isPresent()){
            userToUpdate.get().setName(user.getName());
            userToUpdate.get().setSurname(user.getSurname());
            userToUpdate.get().setBirthdate(user.getBirthdate());
            userToUpdate.get().setLastModifyDate(LocalDateTime.now());
            User userUpdated = userTR.save(userToUpdate.get());
            return userUpdated;
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
        if(userTR.existsByDni(dniU)) {
          userTR.deleteByDni(dniU);
          return true;
        }
        return false;
    }

   }