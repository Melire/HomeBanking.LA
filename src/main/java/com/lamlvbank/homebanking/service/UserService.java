package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long idU);
    Optional<User> findByDni(String dniU);
    User save(User user);
    User register(User user);
    User update(User user);
    boolean deleteById(Long idU);
    boolean deleteByDni(String dniU);
}
