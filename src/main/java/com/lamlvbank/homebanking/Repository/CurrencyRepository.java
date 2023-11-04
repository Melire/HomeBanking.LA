package com.lamlvbank.homebanking.Repository;

import com.lamlvbank.homebanking.Model.Currency;
import com.lamlvbank.homebanking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
 public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    }
