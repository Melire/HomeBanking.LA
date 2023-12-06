package com.lamlvbank.homebanking.service;

import com.lamlvbank.homebanking.model.Transaction;
import com.lamlvbank.homebanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
// import java.util.Random;

@Service
public class IMPTransactionService implements TransactionService {
    @Autowired
    private TransactionRepository tR;

    @Override
    public List<Transaction> findAll() {
        return tR.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long idT) {
        return tR.findById(idT);
    }

    @Override
    public Transaction save(Transaction trans) {
        if(tR.existsByTransN(trans.getTransN())){
            return null;
        }
        return tR.save(trans);
    }

    @Override
    public Transaction update(Transaction trans) {
        Optional<Transaction>optTransaction=tR.findByTransN(trans.getTransN());
        if(optTransaction.isPresent()){
            optTransaction.get().setAmount(trans.getAmount());
            return tR.save(optTransaction.get());  //retorna la entidad actualizada
        }
        return trans; //si no hay cambios entonces retorna nuevamente lo anterior. lo que viene en el json.
    }

    @Override
    public boolean deleteById(Long idT) {
        if(tR.existsById(idT)){
            tR.deleteById(idT);
            return true;
        }
        return false;
    }

    // private Long transactionNGen(){
    //     Long transN = 0L;
    //     Random random = new Random();
    //     do{
    //         transN = Math.abs(random.nextLong() % (999999999 + 1));
    //     }while(tR.existsByTransN(transN));
    // return transN;
    // }
}
