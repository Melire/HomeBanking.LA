package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long idU);
    Optional<User> findByDni(String dniU);
    User save(User user);
    User update(User user);
    boolean deleteById(Long idU);
    boolean deleteByDni(String dniU);

}
