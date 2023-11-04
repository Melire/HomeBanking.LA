package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User getUserById(Long idU);
    Optional<User> findById(Long idU);
    Optional<User> getUserByDni(String dniU);
    User save(User user);
    boolean deleteById(Long idU);
}
