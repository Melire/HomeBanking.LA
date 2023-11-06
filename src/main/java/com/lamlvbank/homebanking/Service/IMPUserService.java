package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.User;
import com.lamlvbank.homebanking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userTR.save(user);
    }
    @Override
    public User update(User user){
        Optional <User> userToUpdate = userTR.findByDni(user.getDni());
        if(userToUpdate.isPresent()){
            userToUpdate.get().setName(user.getName());
            userToUpdate.get().setSurname(user.getSurname());
            userToUpdate.get().setBirthdate(user.getBirthdate());
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