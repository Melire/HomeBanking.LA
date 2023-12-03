package com.lamlvbank.homebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lamlvbank.homebanking.model.Account;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public boolean existsByAccountN(String accountN);
    public boolean existsByCbu(String cbu);
    public boolean existsByAlias(String alias);
    public Optional<Account> findByAccountN(String accountN);
    public Optional<Account> findByAccountNAndCbu(String accountN,String cbu);
}
