package com.lamlvbank.homebanking.service;
import com.lamlvbank.homebanking.model.Transference;

import java.util.List;
import java.util.Optional;

public interface TransferenceService {
    List<Transference> findAll();
    Optional<Transference> findById(Long idT);
    Transference save(Transference transference);
    Transference update(Transference transference);
    boolean deleteById(Long idT);

}
