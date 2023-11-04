package com.lamlvbank.homebanking.Repository;
import com.lamlvbank.homebanking.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByDni(String dni);
    Optional<User> getUserByDni(String dni);
    @Transactional
    @Modifying
    void deleteByDni(String dni);
}