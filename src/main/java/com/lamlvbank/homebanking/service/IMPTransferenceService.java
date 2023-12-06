package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Transference;
import com.lamlvbank.homebanking.model.dto.TransferenceDTO;
import com.lamlvbank.homebanking.repository.TransferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class IMPTransferenceService implements TransferenceService{
    @Autowired
    private TransferenceRepository tR;

    @Autowired
    private AccountService accServ;

    @Override
    public List<Transference> findAll() {
        return tR.findAll();
    }

    @Override
    public List<Transference> findAllByOrigin(Long idO){
        return tR.findAllByOriginIdA(idO);
    }

    @Override
    public List<Transference> findAllByDestiny(Long idD){
        return tR.findAllByDestinyIdA(idD);
    }

    @Override
    public Optional<Transference> findById(Long idT) {
        return tR.findById(idT);
    }

    @Override
    public Transference save(Transference transference) {
        accServ.updateAmounts(transference.getOrigin().getIdA(), transference.getDestiny().getIdA()
                            ,transference.getAmount());
        transference.setCreationDate(LocalDateTime.now());                    
        return tR.save(transference);
    }

    @Override
    public Transference register(TransferenceDTO dto){
            Transference transference = new Transference();
            transference.setTransferenceN(transferenceNGen());
            transference.setReference(dto.getReference());
            transference.setAmount(dto.getAmount());
            transference.addOriginAcc(dto.getIdO());
            transference.addDestinyAcc(dto.getIdD());
            transference.setCreationDate(LocalDateTime.now());
            accServ.updateAmounts(dto.getIdO(), dto.getIdD(), dto.getAmount());
        return tR.save(transference);
    }

    @Override
    public boolean deleteById(Long idT) {
        if(tR.existsById(idT)){
            tR.deleteById(idT);
            return true;
        }
    return false;
    }

//? Método AUXILIAR para generar el numero de transferencia.
    private Long transferenceNGen(){
        Long transfN = 0L;
        Random random = new Random();
        do{
            transfN = Math.abs(random.nextLong() % (999999999 + 1));
        }while(tR.existsByTransferenceN(transfN));
    return transfN;
    }
}
