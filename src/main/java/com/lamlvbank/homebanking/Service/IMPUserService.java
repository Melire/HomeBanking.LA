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
        User user = userTR.findById(idU).get();
        return user;
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
        userTR.deleteById(idU);
        if (userTR.existsById(idU)){
            return false;
        }
        return true;
    }
}
