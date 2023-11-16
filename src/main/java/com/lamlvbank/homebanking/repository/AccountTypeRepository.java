package com.lamlvbank.homebanking.repository;

import com.lamlvbank.homebanking.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {
    boolean existsByName(String name);
}
