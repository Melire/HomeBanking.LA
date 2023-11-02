package com.lamlvbank.homebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lamlvbank.homebanking.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public boolean existsByAccountN(String accountN);

    public boolean existsByCbu(String cbu);

    public boolean existsByAlias(String alias);

}
