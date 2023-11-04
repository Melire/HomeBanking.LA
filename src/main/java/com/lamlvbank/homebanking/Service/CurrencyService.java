package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.Currency;
import com.lamlvbank.homebanking.Model.User;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
  List<Currency> findAll();
  Optional<Currency> findById(Long idC);
  Currency save(Currency currency);
  boolean deleteById(Long idC);

}


