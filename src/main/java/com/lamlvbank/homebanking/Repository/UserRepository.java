package com.lamlvbank.homebanking.Repository;

import com.lamlvbank.homebanking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    Optional<User> getUserByDni(String dni);

}
