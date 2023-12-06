package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Currency;
import com.lamlvbank.homebanking.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IMPCurrencyService implements CurrencyService {
    @Autowired
    private CurrencyRepository cr;

    @Override
    public List<Currency> findAll() {
        return cr.findAll();
    }

    @Override
    public Optional<Currency> findById(Long idC) {
        return cr.findById(idC);
    }

    @Override
    public Currency save(Currency currency) {
        if (cr.existsByName(currency.getName())) {
            return currency;
        }
        currency.setCreationDate(LocalDateTime.now());
    return cr.save(currency);
    }

    @Override
    public Currency update(Currency currency) {
        if (cr.existsById(currency.getIdC())) {
            return cr.save(currency);
        }
        currency.setIdC(0L);
    return currency;
    }

    @Override
    public boolean deleteById(Long idC) {
        if (cr.existsById(idC)) {
            cr.deleteById(idC);
            return true;
        }
        return false;
    }
}
