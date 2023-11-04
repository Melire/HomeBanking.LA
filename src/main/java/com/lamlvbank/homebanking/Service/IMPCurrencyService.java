package com.lamlvbank.homebanking.Service;

import com.lamlvbank.homebanking.Model.Currency;
import com.lamlvbank.homebanking.Repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMPCurrencyService implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyTR;

    @Override
    public List<Currency> findAll() {
        return currencyTR.findAll();
    }

    @Override
    public Optional<Currency> findById(Long idC) {
        return currencyTR.findById(idC);
    }

    @Override
    public Currency save(Currency currency) { return currencyTR.save(currency);
    }

    @Override
    public boolean deleteById(Long idC) {
        currencyTR.deleteById(idC);
        if (currencyTR.existsById(idC)) {
            return false;
        }
        return true;
    }
}
