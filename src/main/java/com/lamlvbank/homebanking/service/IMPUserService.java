package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Account;
import com.lamlvbank.homebanking.model.User;
import com.lamlvbank.homebanking.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IMPUserService implements UserService{

    private  UserRepository userTR;
    private AccountService accSer;
    private PasswordEncoder passEnc;

    public IMPUserService(UserRepository userRep, AccountService accSer) {
        this.userTR = userRep;
        this.accSer = accSer;
        this.passEnc = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> findAll() { return userTR.findAll(); }

    @Override
    public Optional<User> findByDni(String dniU){
        Optional<User> user = userTR.findByDni(dniU);
        if(user.isPresent()) {
           user.get().setPassword("******");
        }
        return user;
    }
    @Override
    public Optional<User> findById(Long idU) {
        Optional<User> user = userTR.findById(idU);
        return user;
    }

    @Override
    public User save(User user) {
        if(userTR.existsByDni(user.getDni())){
            return null;
        }
        user.setCreationDate(LocalDateTime.now());
        user.setLastModifyDate(LocalDateTime.now());
        user.setPassword(this.passEnc.encode(user.getPassword()));
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
            //userToUpdate.get().setPassword(user.getPassword());
            userToUpdate.get().setPassword(this.passEnc.encode(user.getPassword()));
            userToUpdate.get().setBirthdate(user.getBirthdate());
            //userToUpdate.get().setLastModifyDate(LocalDateTime.now());
            User userUpdated = userTR.save(userToUpdate.get());

            //return userUpdated;
            return userTR.findByDni(user.getDni()).get();
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