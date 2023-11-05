package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.User;
import com.lamlvbank.homebanking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IMPUserService implements UserService{
    @Autowired
    private UserRepository userTR;
    @Override
    public List<User> findAll() {
        return userTR.findAll();
    }

    public User getUserById(Long idU){
        if(userTR.findById(idU).isPresent()){
            return userTR.findById(idU).get();
        }
        return null;
    }
    @Override
    public Optional<User> getUserByDni(String dniU){
        return userTR.getUserByDni(dniU);
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
        return userTR.save(user);
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
    public boolean deleteByDni(String dniU) {
        if(userTR.existsByDni(dniU)) {
          userTR.deleteByDni(dniU);
          return true;
        }
        return false;
    }
    @Override
    public boolean updateById(User user){
       if(userTR.existsById(user.getIdU())){
            userTR.save(user);
            return true;
        }
        return false;
    }

   }

