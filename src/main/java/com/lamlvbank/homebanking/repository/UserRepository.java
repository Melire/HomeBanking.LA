package com.lamlvbank.homebanking.repository;
import com.lamlvbank.homebanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    Optional<User> findByDni(String dni);
    void deleteByDni(String dni);
}