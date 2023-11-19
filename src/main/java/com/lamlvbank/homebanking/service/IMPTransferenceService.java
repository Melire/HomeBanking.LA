package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Transference;
import com.lamlvbank.homebanking.repository.TransferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IMPTransferenceService implements TransferenceService{
    @Autowired
    private TransferenceRepository tR;
    @Override
    public List<Transference> findAll() {
        return tR.findAll();
    }

    @Override
    public Optional<Transference> findById(Long idT) {
        return tR.findById(idT);
    }

    @Override
    public Transference save(Transference transference) {
        return tR.save(transference);
    }

    @Override
    public Transference update(Transference transference) {
        return null;
    }

    @Override
    public boolean deleteById(Long idT) {
        return false;
    }
}
