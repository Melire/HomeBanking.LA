package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Currency;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> findAll();
    Optional<Currency> findById(Long idC);
    Currency save(Currency currency);
    boolean deleteById(Long idC);
    Currency update(Currency currency);
}
