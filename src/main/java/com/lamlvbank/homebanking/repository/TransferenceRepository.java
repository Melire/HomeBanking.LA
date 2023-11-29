package com.lamlvbank.homebanking.repository;

import com.lamlvbank.homebanking.model.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferenceRepository extends JpaRepository<Transference,Long> {
    boolean existsByTransferenceN(Long transfN);
}
